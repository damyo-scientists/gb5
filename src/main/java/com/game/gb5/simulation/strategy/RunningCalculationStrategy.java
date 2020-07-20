package com.game.gb5.simulation.strategy;

import com.game.gb5.model.game.result.RunningResult;
import com.game.gb5.model.game.type.BaseResultType;
import com.game.gb5.model.game.unit.DeckPlayer;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Component
public class RunningCalculationStrategy {
    public Queue<DeckPlayer> calculate(List<RunningResult> runningResults) {
        Queue<DeckPlayer> runners = new LinkedList<>();
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
}
