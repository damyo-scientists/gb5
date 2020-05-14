package com.game.gb5.simulation.system;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.InningResult;

import java.util.ArrayList;
import java.util.List;

public class InningSystem {
    private Game game;
    private Deck deck1;
    private Deck deck2;
    private int inning;
    private InningResult inningResult;
    public InningSystem(Game game, int inning) {
        this.inning = inning;
        this.deck1 = game.getMatching().getDeck1();
        this.deck2 = game.getMatching().getDeck2();
    }

    public InningResult playInning() {
        InningResult inningResult = new InningResult();
        List<BattingResult> battingResultList = new ArrayList<>();

        return inningResult;
    }
}
