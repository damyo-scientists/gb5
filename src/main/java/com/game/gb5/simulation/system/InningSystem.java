package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.InningType;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.BattingStrategy;
import com.game.gb5.simulation.strategy.RunningCalculationStrategy;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
@NoArgsConstructor
public class InningSystem {
    private BattingStrategy battingStrategy;
    private RunningCalculationStrategy runningCalculationStrategy;

    @Autowired
    public void setBattingStrategy(BattingStrategy battingStrategy) {
        this.battingStrategy = battingStrategy;
    }

    @Autowired
    public void setRunningCalculationStrategy(RunningCalculationStrategy runningCalculationStrategy) {
        this.runningCalculationStrategy = runningCalculationStrategy;
    }

    /**
     * 이닝을 진행한다.
     */
    public InningResult playInning(int inning, InningType inningType, Squad battingSquad, Squad fieldSquad) {
        List<BattingResult> battingResultList = new ArrayList<>();
        Queue<DeckPlayer> runners = new LinkedList<>();
        int outCount = 0;
        while (!isInningOver(outCount)) {
            DeckPlayer batter = this.pickBatter(battingSquad);
            BattingResult battingResult = battingStrategy.batAndRun(batter, runners, fieldSquad, outCount);
            battingResultList.add(battingResult);
            runners = runningCalculationStrategy.calculate(battingResult.getRunningResultList());
            outCount = calculateOutCount(battingResult);
        }
        // 진루 내역 초기화
        battingSquad.getLineup().forEach(player -> player.setRunningBase(0));
        return InningResult.builder().inning(inning).inningType(inningType).battingResultList(battingResultList).build();
    }

    private DeckPlayer pickBatter(Squad battingSquad) {
        return battingSquad.getLineup().poll();
    }

    private boolean isInningOver(int outCount) {
        return outCount >= 3;
    }

    private int calculateOutCount(BattingResult battingResult) {
        List<RunningResult> runningResults = battingResult.getRunningResultList();
        RunningResult lastRunningResult = runningResults.get(runningResults.size() - 1);
        return lastRunningResult.getOutCount();
    }
}
