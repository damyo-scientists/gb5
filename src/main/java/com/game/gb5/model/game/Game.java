package com.game.gb5.model.game;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.config.GameOptions;
import com.game.gb5.model.game.config.GameState;
import com.game.gb5.model.game.config.GameType;
import com.game.gb5.model.matching.Matching;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game extends BaseEntity {
    @OneToOne
    private Matching matching;
    @Enumerated
    private GameType gameType;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private GameOptions gameOptions;
    @Enumerated
    private GameState gameState;

    public void onReady(GameOptions gameOptions) {
        this.onReady(gameOptions, true);
    }

    public void onReady(GameOptions gameOptions, boolean autoCount) {
        this.gameOptions = gameOptions;
        this.gameState = GameState.ON_READY_TO_START;
    }
}
