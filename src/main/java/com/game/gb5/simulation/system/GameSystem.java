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
    private Squad deck1Squad;
    private Squad deck2Squad;

    public GameSystem(Game game) {
        this.game = game;
        this.gameOptions = game.getGameOptions();

        this.deck1Squad = createSquad(game.getMatching().getDeck1());
        this.deck2Squad = createSquad(game.getMatching().getDeck2());
    }

    private Squad createSquad(Deck deck) {
        Queue<Batter> lineup = createLineup(deck);
        List<Batter> bench = createBench(deck);
        return Squad.builder().lineup(lineup).bench(bench).build();
    }

    private Queue<Batter> createLineup(Deck deck) {
        //1루>2루>MF>SS>3루
        Queue<Batter> lineup = new LinkedList<>();
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.FIRST_BASE), Position.FIRST_BASE));
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.SECOND_BASE), Position.SECOND_BASE));
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.MID_FIELDER), Position.MID_FIELDER));
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.SHORT_STOP), Position.SHORT_STOP));
        lineup.offer(createBatter(deck.getDeckCharacters().get(Position.THIRD_BASE), Position.THIRD_BASE));
        return lineup;
    }

    private List<Batter> createBench(Deck deck) {
        List<Batter> bench = new ArrayList<>();
        bench.add(createBatter(deck.getDeckCharacters().get(Position.BENCH1), Position.BENCH1));
        bench.add(createBatter(deck.getDeckCharacters().get(Position.BENCH2), Position.BENCH2));
        bench.add(createBatter(deck.getDeckCharacters().get(Position.BENCH3), Position.BENCH3));
        return bench;
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
