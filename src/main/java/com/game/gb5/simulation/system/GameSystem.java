package com.game.gb5.simulation.system;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.config.GameOptions;
import com.game.gb5.model.game.result.InningResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameSystem {
    private Game game;
    private GameOptions gameOptions;
    private Queue<Batter> deck1Lineup;
    private Queue<Batter> deck2Lineup;
    private List<Batter> deck1Bench;
    private List<Batter> deck2Bench;

    public GameSystem(Game game) {
        this.game = game;
        this.gameOptions = game.getGameOptions();
    }

    private Queue<Batter> createLineup(Deck deck) {
        //1루>2루>MF>SS>3루
        Queue<Batter> lineup = new LinkedList<>();
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.FIRST_BASE), Position.FIRST_BASE));
        // todo
        return null;
    }

    private Batter createBatter(DeckCharacter deckCharacter, Position position) {
        return Batter.builder().deckCharacter(deckCharacter).position(position).stamina(100).build();
    }

    public String start() {
        List<InningResult> inningResultLIst = new ArrayList<>();
        for (int i = 0; i < gameOptions.getInning(); i++) {
            InningResult inningResult = new InningSystem(game, i).playInning();
            inningResultLIst.add(inningResult);
        }
        String gameResult = "";
        return gameResult;
    }
}
