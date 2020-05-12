package com.game.gb5.simulation;

import com.game.gb5.model.Deck;
import com.game.gb5.model.game.Game;

public class InningSystem {
    private Game game;
    private Deck deck1;
    private Deck deck2;
    private int inning;
    private String inningResult;
    public InningSystem(Game game, int inning) {
        this.inning = inning;
        this.deck1 = game.getMatching().getDeck1();
        this.deck2 = game.getMatching().getDeck2();
    }

    public String start() {
        return inningResult;
    }
}
