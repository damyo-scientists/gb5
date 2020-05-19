package com.game.gb5.simulation.system;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.BattingResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattingSystem {
    private Game game;
    private Squad battingSquad;
    private Squad fieldSquad;

    public BattingResult playBatting(Game game, Squad battingSquad, Squad fieldSquad, Batter batter) {
        this.game = game;
        this.battingSquad = battingSquad;
        this.fieldSquad = fieldSquad;


        // 공식에 따른 배팅 및 수비 진행
        return new BattingResult();
    }
}
