package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.InningType;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InningSystem {
    private InningType inningType;
    private int inning;
    private BattingSystem battingSystem;

    @Autowired
    public void setBattingSystem(BattingSystem battingSystem) {
        this.battingSystem = battingSystem;
    }

    public void initialize(int inning, InningType inningType) {
        this.inning = inning;
        this.inningType = inningType;
    }

    public InningResult playInning(Squad battingSquad, Squad fieldSquad) {
        List<BattingResult> battingResultList = new ArrayList<>();
        int outCount = 0;
        while (outCount < 3) {
            DeckPlayer batter = battingSquad.getLineup().poll();
            if (batter == null) {
                // Base loaded, 2 outs 처리
                throw new NullPointerException();
            }
            BattingResult battingResult = playBatting(batter, fieldSquad, outCount);
            battingResultList.add(battingResult);

            outCount = calculateOutCount(battingResult);
        }
        // 진루 내역 초기화
        battingSquad.getLineup().forEach(player -> player.setRunningBase(0));
        return InningResult.builder().inning(inning).inningType(inningType).battingResultList(battingResultList).build();
    }

    private BattingResult playBatting(DeckPlayer batter, Squad fieldSquad, int outCount) {
        return battingSystem.playBatting(batter, fieldSquad, outCount);
    }

    private int calculateOutCount(BattingResult battingResult) {
        List<RunningResult> runningResults = battingResult.getRunningResultList();
        RunningResult lastRunningResult = runningResults.get(runningResults.size() - 1);
        return lastRunningResult.getOutCount();
    }
}
