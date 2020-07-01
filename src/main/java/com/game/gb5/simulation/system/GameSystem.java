package com.game.gb5.simulation.system;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.config.GameOptions;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.model.game.type.InningType;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.system.helper.SquadMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GameSystem {
    private SquadMaker squadMaker;
    private InningSystem inningSystem;

    @Autowired
    public void setSquadMaker(SquadMaker squadMaker) {
        this.squadMaker = squadMaker;
    }

    @Autowired
    public void setInningSystem(InningSystem inningSystem) {
        this.inningSystem = inningSystem;
    }

    public List<InningResult> start(Game game) {
        GameOptions gameOptions = game.getGameOptions();
        game.getMatching().getDeck1().getDeckCharacters().forEach((position, deckCharacter) -> {
            log.info("post : " + position + ", char : " + deckCharacter);
        });
        Squad deck1Squad = squadMaker.createSquad(game.getMatching().getDeck1());
        Squad deck2Squad = squadMaker.createSquad(game.getMatching().getDeck2());
        List<InningResult> inningResultLIst = new ArrayList<>();
        for (int i = 0; i < gameOptions.getInning(); i++) {
            InningResult inningResultFirst = inningSystem.playInning(i, InningType.FIRST, deck1Squad, deck2Squad);
            InningResult inningResultSecond = inningSystem.playInning(i, InningType.LAST, deck2Squad, deck1Squad);
            inningResultLIst.add(inningResultFirst);
            inningResultLIst.add(inningResultSecond);
        }
        return inningResultLIst;
    }
}
