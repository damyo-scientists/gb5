package com.game.gb5.simulation.system.helper;

import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.game.unit.DeckPlayer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HittingRateResultCalculator {
    public double calculateFinalHittingProbability(DeckPlayer batter, DeckPlayer fielder) {
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
