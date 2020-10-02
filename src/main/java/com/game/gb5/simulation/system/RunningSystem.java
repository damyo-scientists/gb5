package com.game.gb5.simulation.system;

import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.InplayRunningStrategy;
import com.game.gb5.utils.DistributedRandomNumberPicker;
import com.game.gb5.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

@Component
public class RunningSystem {
    @Autowired
    private InplayRunningStrategy inplayRunningStrategy;

    @Autowired
    private DistributedRandomNumberPicker distributedRandomNumberPicker;

    public List<RunningResult> run(DeckPlayer batter, Queue<DeckPlayer> runners, Squad fieldSquad, int currentOutCount, double hittingRandomResult, double hittingRateResult) {
        // 루타 측정
        double hittingQuality = hittingRandomResult / 2 + MathUtils.clamp(hittingRateResult, 0, 0.5);
        int hitBaseNumber = calculateBaseHitNumber(batter, hittingQuality);
        runners.add(batter);
        List<RunningResult> runningResults = inplayRunningStrategy.simulateInplay(runners, fieldSquad, hitBaseNumber, hittingQuality, currentOutCount);
        return runningResults;
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
}
