package com.game.gb5.simulation.strategy;

import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.BaseResultType;
import com.game.gb5.model.game.type.RunningType;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.condition.RunningCondition;
import com.game.gb5.utils.DistributedRandomNumberPicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InplayRunningStrategy {
    private DistributedRandomNumberPicker distributedRandomNumberPicker;

    @Autowired
    public InplayRunningStrategy(DistributedRandomNumberPicker distributedRandomNumberPicker) {
        this.distributedRandomNumberPicker = distributedRandomNumberPicker;
    }

    public List<RunningResult> simulateInplay(Queue<DeckPlayer> runners, Squad fieldSquad, int hitBaseNumber, double hittingQuality, int currentOutCount) {
        assert runners.peek() != null;
        final AtomicBoolean isRunnerStop = new AtomicBoolean(false);
        final AtomicInteger outCount = new AtomicInteger(currentOutCount);
        boolean isHomeReachable = runners.peek().getRunningBase() + hitBaseNumber > 3;
        List<RunningResult> runningResults = new ArrayList<>();
        runners.forEach(runner -> {
            if (outCount.get() < 3) {
                // Base Running
                BaseResultType baseResultType = BaseResultType.NONE;
                int currentBase = runner.getRunningBase();

                if (new RunningCondition().isAlwaysSafe(currentBase, hitBaseNumber)) {
                    baseResultType = BaseResultType.HOME_IN;
                } else {

                    int runnerStopCount = isRunnerStop.get() ? 0 : -1;
                    int baseCountToMove = currentBase + hitBaseNumber - runnerStopCount;

                    double inplayCoefficient = this.calculateInplayCoefficientByHittingDirection(runner, fieldSquad, baseCountToMove, isHomeReachable, hittingQuality);
                    double stopRunnerRate = (10 + inplayCoefficient / hittingQuality) / 100;
                    double outRate = (10 + inplayCoefficient / hittingQuality) / 100;
                    double safeRate = 1.0 - stopRunnerRate - outRate;
                    List<Double> runningRateList = Arrays.asList(outRate, stopRunnerRate, safeRate);
                    int runningValue = distributedRandomNumberPicker.getDistributedRandomNumber(runningRateList);
                    RunningType runningType = RunningType.findByRunningValue(runningValue);

                    switch (runningType) {
                        case OUTS:
                            baseResultType = BaseResultType.OUTS_ON_BASE;
                            isRunnerStop.set(false);
                            outCount.incrementAndGet();
                            break;
                        case STOP_RUNNING:
                            // 주루 저지 체크
                            // 이미 주루 저지된 경우는 원래대로 되돌림 (후속 주자는 검증하지 않고 세이프 처리)s
                            if (isRunnerStop.get()) {
                                isRunnerStop.set(false);
                            } else {
                                baseCountToMove -= 1;
                                isRunnerStop.set(true);
                            }
                            baseResultType = BaseResultType.findByBaseNumber(baseCountToMove);
                            break;
                        case SAFE:
                            baseResultType = BaseResultType.findByBaseNumber(baseCountToMove);
                            isRunnerStop.set(false);
                            break;
                    }
                }
                RunningResult runningResult = RunningResult.builder().runner(runner).baseResultType(baseResultType).outCount(outCount.get()).build();
                runningResults.add(runningResult);
            } else {
                RunningResult runningResult = RunningResult.builder().runner(runner).baseResultType(BaseResultType.THREE_OUT_NO_RESULT).outCount(outCount.get()).build();
                runningResults.add(runningResult);
            }
        });
        return runningResults;
    }


    private double calculateInplayCoefficientByHittingDirection(DeckPlayer runner, Squad fieldSquad, int baseSum, boolean isHomeReachable, double hittingQuality) {
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
        return this.calculateInplayCoefficient(runner, fieldSquad, fielderCoefficients, hittingQuality);
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
}
