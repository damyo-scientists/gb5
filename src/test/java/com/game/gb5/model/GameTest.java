package com.game.gb5.model;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.GameOptions;
import com.game.gb5.model.game.GameState;
import com.game.gb5.model.game.GameType;
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

    @Test
    public void testGameStart() {
        // game has game options, option applied before onReady
        GameOptions gameOptions = new GameOptions();
        game.onReady(gameOptions, false);
        Assert.assertEquals(GameState.ON_READY_TO_START, game.getGameState());

        // ok, game started, game status have to be changed.
        game.start();
        Assert.assertEquals(GameState.IN_GAME, game.getGameState());
    }
}
