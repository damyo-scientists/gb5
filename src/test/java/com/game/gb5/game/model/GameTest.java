package com.game.gb5.game.model;

import com.game.gb5.deck.model.Deck;
import com.game.gb5.game.model.Game;
import com.game.gb5.game.model.GameOptions;
import com.game.gb5.game.model.GameStatus;
import com.game.gb5.game.model.GameType;
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
        this.game = Game.builder().gameType(GameType.VERSUS_PLAYER).deck1(deck1).deck2(deck2).build();
    }

    @Test
    public void testGameWait() {
        Assert.assertEquals(GameStatus.WAIT_TO_READY, game.getGameStatus());
    }

    @Test
    public void testGameStart() {
        // game has game options, option applied before onReady
        GameOptions gameOptions = new GameOptions();
        game.onReady(gameOptions, false);
        Assert.assertEquals(GameStatus.ON_READY_TO_START, game.getGameStatus());

        // ok, game started, game status have to be changed.
        game.start();
        Assert.assertEquals(GameStatus.IN_GAME, game.getGameStatus());
    }
}
