package com.game.gb5.model;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.config.GameState;
import com.game.gb5.model.game.config.GameType;
import com.game.gb5.model.matching.Matching;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game;

    @Before
    public void build() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        // let's think about builder pattern

        // game needs deck, and game has a type (vs ai, vs player)
        Matching matching = new Matching();
        matching.setDeck1(deck1);
        matching.setDeck2(deck2);
        this.game = Game.builder().gameType(GameType.VERSUS_PLAYER).matching(matching).build();
    }

    @Test
    public void testGameWait() {
        Assert.assertEquals(GameState.WAIT_TO_READY, game.getGameState());
    }
}
