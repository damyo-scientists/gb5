package com.game.gb5.simulation.strategy;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.unit.Batter;
import com.game.gb5.model.game.unit.Squad;

public interface BattingStrategy {
    public BattingResult bat(Squad battingSquad, Squad fieldSquad, Batter batter);
}
