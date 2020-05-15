package com.game.gb5.simulation.system;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.config.GameOptions;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.simulation.system.helper.SquadMaker;

import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    private Game game;
    private GameOptions gameOptions;
    private Squad deck1Squad;
    private Squad deck2Squad;

    public GameSystem(Game game) {
        this.game = game;
        this.gameOptions = game.getGameOptions();
        SquadMaker squadMaker = new SquadMaker();
        this.deck1Squad = squadMaker.createSquad(game.getMatching().getDeck1());
        this.deck2Squad = squadMaker.createSquad(game.getMatching().getDeck2());
    }

    public List<InningResult> start() {
        List<InningResult> inningResultLIst = new ArrayList<>();
        for (int i = 0; i < gameOptions.getInning(); i++) {
            InningResult inningResultFirst = new InningSystem(game, i, InningType.FIRST, deck1Squad, deck2Squad).playInning();
            InningResult inningResultSecond = new InningSystem(game, i, InningType.LAST, deck2Squad, deck1Squad).playInning();
            inningResultLIst.add(inningResultFirst);
            inningResultLIst.add(inningResultSecond);
        }
        return inningResultLIst;
    }
}
