package com.game.gb5.simulation.strategy;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;

import java.util.Queue;

public interface BattingStrategy {
    public BattingResult bat(DeckPlayer batter, Squad fieldSquad, Queue<DeckPlayer> runners);
}
