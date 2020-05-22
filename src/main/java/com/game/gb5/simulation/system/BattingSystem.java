package com.game.gb5.simulation.system;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.strategy.BattingStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@Component
public class BattingSystem {
    private BattingStrategy battingStrategy;
    private Queue<DeckPlayer> runners;

    public BattingSystem() {
        this.runners = new LinkedList<>();
    }

    public BattingResult playBatting(DeckPlayer batter, Squad fieldSquad) {
        // 타자 칩니다
        BattingResult battingResult = battingStrategy.bat(batter, fieldSquad, runners);


        // 공식에 따른 배팅 및 수비 진행
        return new BattingResult();
    }

    @Autowired
    public void setBattingStrategy(BattingStrategy battingStrategy) {
        this.battingStrategy = battingStrategy;
    }
}
