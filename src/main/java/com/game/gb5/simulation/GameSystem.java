package com.game.gb5.simulation;

import com.game.gb5.model.Game;
import com.game.gb5.model.GameOptions;

import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    private Game game;
    private GameOptions gameOptions;
    public GameSystem(Game game) {
        this.game = game;
        this.gameOptions = game.getGameOptions();
    }

    public String start() {
        List<String> inningResultLIst = new ArrayList<>();
        for (int i=0; i<gameOptions.getInning(); i++) {
            String inningResult = new InningSystem(game, i).start();
            inningResultLIst.add(inningResult);
        }
        String gameResult = "";
        return gameResult;
    }
}
