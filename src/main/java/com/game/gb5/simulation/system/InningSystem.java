package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.InningResult;

import java.util.ArrayList;
import java.util.List;

public class InningSystem {
    private InningType inningType;
    private Game game;
    private Squad battingSquad;
    private Squad fieldSquad;
    private int inning;
    private InningResult inningResult;
    public InningSystem(Game game, int inning, InningType inningType, Squad battingSquad, Squad fieldSquad) {
        this.inning = inning;
        this.inningType = inningType;
        this.battingSquad = battingSquad;
        this.fieldSquad = fieldSquad;
    }

    public InningResult playInning() {
        InningResult inningResult = new InningResult();
        List<BattingResult> battingResultList = new ArrayList<>();
        int outCount = 0;
        while(outCount < 3) {
            Batter batter = battingSquad.getLineup().poll();
            BattingResult battingResult = new BattingSystem().playBatting(batter);
            battingResultList.add(battingResult);
            battingSquad.getLineup().offer(batter);
        }
        inningResult.setBattingResultList(battingResultList);
        return inningResult;
    }
}
