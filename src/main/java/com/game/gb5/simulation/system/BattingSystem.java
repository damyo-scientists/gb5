package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.BaseResultType;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.BattingStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@Component
public class BattingSystem {
    private BattingStrategy battingStrategy;
    private Queue<DeckPlayer> runners;

    public BattingSystem() {
        this.runners = new LinkedList<>();
    }

    public BattingResult playBatting(DeckPlayer batter, Squad fieldSquad, int outCount) {
        // 공식에 따른 배팅 및 수비 진행
        BattingResult battingResult = battingStrategy.bat(batter, fieldSquad, runners, outCount);
        List<DeckPlayer> runners = this.calculateRunners(battingResult.getRunningResultList());
        battingResult.setRuners(runners);
        return battingResult;
    }

    private List<DeckPlayer> calculateRunners(List<RunningResult> runningResults) {
        List<DeckPlayer> runners = new ArrayList<>();
        for (RunningResult runningResult : runningResults) {
            BaseResultType baseResultType = runningResult.getBaseResultType();
            if (baseResultType.getBaseNumber() > 0 && baseResultType.getBaseNumber() < 4) {
                DeckPlayer runner = runningResult.getRunner();
                runner.setRunningBase(runningResult.getBaseResultType().getBaseNumber());
                runners.add(runningResult.getRunner());
            }
        }
        return runners;
    }

    @Autowired
    public void setBattingStrategy(BattingStrategy battingStrategy) {
        this.battingStrategy = battingStrategy;
    }
}
