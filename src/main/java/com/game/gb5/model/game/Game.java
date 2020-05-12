package com.game.gb5.model.game;

import com.game.gb5.model.Deck;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.simulation.GameSystem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game extends BaseEntity {
    @OneToOne
    private Matching matching;
    @Enumerated
    private GameType gameType;
    @OneToOne(mappedBy = "game")
    private GameOptions gameOptions;
    @Enumerated
    private GameState gameState;

    @Builder
    public Game(GameType gameType, Matching matching) {
        this.matching = matching;
        this.gameType = gameType;
        this.gameState = GameState.WAIT_TO_READY;
        // gameOptions = gameDefault
    }

    public void onReady(GameOptions gameOptions) {
        this.onReady(gameOptions, true);
    }

    public void onReady(GameOptions gameOptions, boolean autoCount) {
        this.gameOptions = gameOptions;
        this.gameState = GameState.ON_READY_TO_START;

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
        this.gameState = GameState.IN_GAME;
        // inning start
        String gameResult = new GameSystem(this).start();
        return gameResult;
    }
}
