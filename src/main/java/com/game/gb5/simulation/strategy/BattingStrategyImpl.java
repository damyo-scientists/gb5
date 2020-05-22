package com.game.gb5.simulation.strategy;

import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.utils.DistributedRandomNumberPicker;
import com.game.gb5.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BattingStrategyImpl implements BattingStrategy {
    private DistributedRandomNumberPicker distributedRandomNumberPicker;

    @Autowired
    public void setDistributedRandomNumberPicker(DistributedRandomNumberPicker distributedRandomNumberPicker) {
        this.distributedRandomNumberPicker = distributedRandomNumberPicker;
    }

    @Override
    public BattingResult bat(DeckPlayer batter, Squad fieldSquad, Queue<DeckPlayer> runners) {
        // calculateInclination
        List<Double> hittingInclination = batter.getDeckCharacter().getCharacter().getHittingInclination();
        DeckPlayer selectedFielder = this.selectFielder(hittingInclination, fieldSquad);
        // 안타여부 확인 : 타자의 4개 공격 스탯(타격 파워, 타격 정확도)과 , 1차 수비수의 4개 수비 스탯(수비력, 순발력)을 비교하여, 안타 확률을 계산
        double hittingRateResult = calculateHitRateResult(batter, selectedFielder);
        double hittingRandomResult = Math.random();
        boolean isHit = hittingRandomResult < hittingRateResult;
        // 안타시 주루 결정
        if (isHit) {
            // 루타 측정
            double hittingQuality = hittingRandomResult / 2 + MathUtils.clamp(hittingRateResult, 0, 0.5);
            int hitBaseNumber = calculateBaseHitNumber(batter, hittingQuality);
            runners.add(batter);
            this.calculateInplay(runners, fieldSquad, hitBaseNumber, hittingQuality);
        } else {

        }
        return BattingResult.builder().hitRandomResult(hittingRateResult).build();
    }

    /**
     * 타격에 따른 수비수 선택
     *
     * @param hittingInclination
     * @param fieldSquad
     * @return
     */
    private DeckPlayer selectFielder(List<Double> hittingInclination, Squad fieldSquad) {
        int randomIndex = distributedRandomNumberPicker.getDistributedRandomNumber(hittingInclination);
        Position selectedPosition = Position.findByPositionNumber(randomIndex);
        return fieldSquad.getLineup().stream().filter(player -> player.getPosition().getPositionNumber() == selectedPosition.getPositionNumber()).findAny().orElseThrow();
    }

    private double calculateStopRunnerRate(DeckPlayer runner, Squad fieldSquad, int baseSum, boolean isHomeReachable, double hittingQuality) {
        double firstBaseFielderCoefficient = 0;
        double secondBaseFielderCoefficient = 0;
        double thirdBaseFielderCoefficient = 0;
        double midFielderCoefficient = 0;
        double shortStopFielderCoefficient = 0;
        switch (baseSum) {
            case 1:
                if (isHomeReachable) {
                    firstBaseFielderCoefficient = 0.5;
                    secondBaseFielderCoefficient = 0.5;
                } else {
                    firstBaseFielderCoefficient = 0.625;
                    secondBaseFielderCoefficient = 0.375;
                }
                break;
            case 2:
                if (isHomeReachable) {
                    secondBaseFielderCoefficient = 0.5;
                    midFielderCoefficient = 0.5;
                } else {
                    midFielderCoefficient = 0.25;
                    shortStopFielderCoefficient = 0.5;
                    secondBaseFielderCoefficient = 0.25;
                }
                break;
            case 3:
                if (isHomeReachable) {
                    midFielderCoefficient = 0.5;
                    shortStopFielderCoefficient = 0.5;
                } else {
                    thirdBaseFielderCoefficient = 0.625;
                    shortStopFielderCoefficient = 0.375;
                }
                break;
            case 4:
            default:
                shortStopFielderCoefficient = 0.5;
                midFielderCoefficient = 0.5;
                break;
        }
        List<Double> fielderCoefficients = Arrays.asList(firstBaseFielderCoefficient, secondBaseFielderCoefficient, thirdBaseFielderCoefficient, midFielderCoefficient, shortStopFielderCoefficient);
        double inplayCoefficient = this.calculateInplayCoefficient(runner, fieldSquad, fielderCoefficients, hittingQuality);
        return (10 + inplayCoefficient / hittingQuality) / 100;
    }

    private void calculateInplay(Queue<DeckPlayer> runners, Squad fieldSquad, int hitBaseNumber, double hittingQuality) {
        if (runners.size() > 1) {
            final AtomicInteger stopRunnerCount = new AtomicInteger(0);
            boolean isHomeReachable = runners.peek().getRunningBase() + hitBaseNumber > 3;
            runners.forEach(runner -> {
                int currentBase = runner.getRunningBase();
                int baseSum = currentBase + hitBaseNumber - stopRunnerCount.get();
                double calculateStopRunnerRate = this.calculateStopRunnerRate(runner, fieldSquad, baseSum, isHomeReachable, hittingQuality);
                double rand = Math.random();
                // 주루 저지 (아웃이 아닌, 해당 루로 진루하지 못하게 함)
                // todo calculate!
                boolean stopRunner = calculateStopRunnerRate > rand;
                if (stopRunner) {
                    stopRunnerCount.getAndIncrement();
                    baseSum -= 1;
                }
            });
        }
    }

    private double calculateInplayCoefficient(DeckPlayer runner, Squad fielderSquad, List<Double> fielderCoefficients, double hittingQuality) {
        double fielderStatsSum = fielderSquad.getLineup().stream().mapToDouble(fielder -> {
            CharacterStatus fielderStatus = fielder.getDeckStatus();
            double fielderStat = fielderStatus.getReflexes() + fielderStatus.getDefending()
                    + fielderStatus.getThrowingSpeed() + fielderStatus.getThrowingAccuracy();
            return fielderStat * fielderCoefficients.get(fielder.getPosition().getPositionNumber());
        }).sum();

        CharacterStatus runnerStatus = runner.getDeckStatus();
        return (fielderStatsSum - runnerStatus.getRunningSpeed() - runnerStatus.getSense()) / 200.0;
    }

    private int calculateBaseHitNumber(DeckPlayer batter, double hittingQUality) {
        CharacterStatus batterStatus = batter.getDeckStatus();
        double battingStatusSum = batterStatus.getBattingPower() + batterStatus.getBattingAccuracy() + batterStatus.getRunningSpeed() + batterStatus.getSense();
        double secondBaseHitProbability = (hittingQUality * 200 + battingStatusSum * 1.8) / 800;
        double thirdBaseHitProbability = (hittingQUality * 200 + battingStatusSum * 0.1) / 800;
        double firstBaseHitProbability = 1.0 - secondBaseHitProbability - thirdBaseHitProbability;
        List<Double> baseHitList = Arrays.asList(firstBaseHitProbability, secondBaseHitProbability, thirdBaseHitProbability);
        return distributedRandomNumberPicker.getDistributedRandomNumber(baseHitList) + 1;
    }

    private double calculateHitRateResult(DeckPlayer batter, DeckPlayer fielder) {
        CharacterStatus batterStatus = batter.getDeckStatus();
        CharacterStatus fielderStatus = fielder.getDeckStatus();
        final double BASE_HIT_RATE = 0.3;
        double batterAdvantageBonusCoefficient = 1.0;
        double defenderAdvantageBonusCoefficient = 1.5;
        double decreaseWhileSubstatCoefficient = 0.5;
        double[] hitRateMultiplyValue = {0.0000083, 0.00025, 0.047};
        double[] statDiffs = new double[4];
        statDiffs[0] = batterStatus.getBattingPower() - fielderStatus.getDefending();
        statDiffs[1] = batterStatus.getBattingAccuracy() - fielderStatus.getReflexes();
        statDiffs[2] = batterStatus.getRunningSpeed() - fielderStatus.getThrowingSpeed();
        statDiffs[3] = batterStatus.getSense() - fielderStatus.getThrowingAccuracy();
        return BASE_HIT_RATE + Arrays.stream(statDiffs).reduce(0, (subTotal, statDiff) -> {
            double hitChanceByStatDiff = (Math.pow(Math.abs(statDiff), 3) * hitRateMultiplyValue[0]
                    + Math.pow(Math.abs(statDiff), 2) * hitRateMultiplyValue[1] + Math.abs(statDiff) * hitRateMultiplyValue[2]) / 100;
            if (statDiff < 0) {
                hitChanceByStatDiff = hitChanceByStatDiff * defenderAdvantageBonusCoefficient;
            } else {
                hitChanceByStatDiff = hitChanceByStatDiff * batterAdvantageBonusCoefficient;
            }

            // 안타 확률에 더 적게 영향을 주는 서브 스탯들의 적용 값 감소
            if (statDiff == statDiffs[2] || statDiff == statDiffs[3]) {
                hitChanceByStatDiff = hitChanceByStatDiff * decreaseWhileSubstatCoefficient;
            }
            return subTotal + hitChanceByStatDiff;
        });
    }
}
