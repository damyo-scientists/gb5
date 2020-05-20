package com.game.gb5.simulation.system;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.unit.Batter;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.BattingStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BattingSystem {
    private BattingStrategy battingStrategy;
    private Game game;
    private Squad battingSquad;
    private Squad fieldSquad;

    public BattingResult playBatting(Game game, Squad battingSquad, Squad fieldSquad, Batter batter) {
        this.game = game;
        this.battingSquad = battingSquad;
        this.fieldSquad = fieldSquad;

        // 타자 칩니다
        BattingResult battingResult = battingStrategy.bat(battingSquad, fieldSquad, batter);


        // 공식에 따른 배팅 및 수비 진행
        return new BattingResult();
    }

    @Autowired
    public void setBattingStrategy(BattingStrategy battingStrategy) {
        this.battingStrategy = battingStrategy;
    }
}
