package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.result.HitResult;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.BaseResultType;
import com.game.gb5.model.game.type.BattingType;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.BattingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Component
public class BattingSystem {
    @Autowired
    private RunningSystem runningSystem;
    private BattingStrategy battingStrategy;

    @Autowired
    public void setBattingStrategy(BattingStrategy battingStrategy) {
        this.battingStrategy = battingStrategy;
    }

    public BattingResult hitAndRun(DeckPlayer batter, Queue<DeckPlayer> runners, Squad fieldSquad, int currentOutCount, double hittingRandomResult, double hittingRateResult) {
        HitResult hitResult = battingStrategy.hit(batter, fieldSquad);
        if (hitResult.isHit()) {
            List<RunningResult> runningResults = runningSystem.run(batter, runners, fieldSquad, currentOutCount, hittingRandomResult, hittingRateResult);
            BattingType battingType = checkBattingType(runningResults);
            int scoringCount = (int) runningResults.stream().filter(r -> r.getBaseResultType() == BaseResultType.HOME_IN).count();
            int outCount = runningResults.stream().map(RunningResult::getOutCount).max(Integer::compare).orElseThrow();
            return BattingResult.builder().hitRandomResult(hittingRateResult).battingType(battingType)
                    .runningResultList(runningResults).outCount(outCount).scoringCount(scoringCount).build();
        } else {
            BattingType battingType = BattingType.HIT_FAILED_OUT;
            return BattingResult.builder().hitRandomResult(hittingRandomResult).battingType(battingType).outCount(1).runningResultList(new ArrayList<>()).build();
        }
    }

    private BattingType checkBattingType(List<RunningResult> runningResults) {
        BattingType battingType;
        if (runningResults.size() > 1
                && runningResults.get(runningResults.size() - 1).getBaseResultType() == BaseResultType.FIRST_BASE_RUN
                && runningResults.get(runningResults.size() - 2).getBaseResultType() == BaseResultType.OUTS_ON_BASE) {
            battingType = BattingType.FIELDERS_CHOICE;
        } else {
            if (runningResults.get(runningResults.size() - 1).getBaseResultType() == BaseResultType.OUTS_ON_BASE) {
                battingType = BattingType.GROUNDED_OUT;
            } else {
                battingType = BattingType.HIT;
            }
        }
        return battingType;
    }

}
