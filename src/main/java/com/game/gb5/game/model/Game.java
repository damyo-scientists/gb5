package com.game.gb5.game.model;

import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.game.simulation.GameSystem;
import lombok.Builder;
import lombok.Getter;

public class Game extends BaseEntity {
    private GameType gameType;
    @Getter
    private Deck deck1;
    @Getter
    private Deck deck2;
    @Getter
    private GameOptions gameOptions;
    @Getter
    private GameStatus gameStatus;

    @Builder
    public Game(GameType gameType, Deck deck1, Deck deck2) {
        this.gameType = gameType;
        this.deck1 = deck1;
        this.deck2 = deck2;
        this.gameStatus = GameStatus.WAIT_TO_READY;
        // gameOptions = gameDefault
    }

    public void onReady(GameOptions gameOptions) {
        this.onReady(gameOptions, true);
    }

    public void onReady(GameOptions gameOptions, boolean autoCount) {
        this.gameOptions = gameOptions;
        this.gameStatus = GameStatus.ON_READY_TO_START;

        if (autoCount) {
            countToStart();
        }
    }

    public void countToStart() {
        countToStart(false);
    }

    public void countToStart(boolean countSkip) {
        if (!countSkip) {
            // count
        }

        start();
    }

    public String start() {
        this.gameStatus = GameStatus.IN_GAME;
        // inning start
        String gameResult = new GameSystem(this).start();
        return gameResult;
    }
}
