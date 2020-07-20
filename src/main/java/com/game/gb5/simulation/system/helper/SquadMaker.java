package com.game.gb5.simulation.system.helper;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
@Slf4j
public class SquadMaker {
    public Squad createSquad(Deck deck) {
        Queue<DeckPlayer> lineup = createLineup(deck);
        List<DeckPlayer> bench = createBench(deck);
        return Squad.builder().lineup(lineup).bench(bench).build();
    }

    private DeckPlayer createDeckPlayer(DeckCharacter deckCharacter, Position position) {
        return DeckPlayer.builder().deckCharacter(deckCharacter).position(position).stamina(100).deckStatus(deckCharacter.getCharacter().getCharacterStatus()).build();
    }

    private Queue<DeckPlayer> createLineup(Deck deck) {
        //1루>2루>MF>SS>3루
        Queue<DeckPlayer> lineup = new LinkedList<>();
        lineup.add(createDeckPlayer(deck.getDeckCharacters().get(Position.FIRST_BASE), Position.FIRST_BASE));
        lineup.add(createDeckPlayer(deck.getDeckCharacters().get(Position.SECOND_BASE), Position.SECOND_BASE));
        lineup.add(createDeckPlayer(deck.getDeckCharacters().get(Position.MID_FIELDER), Position.MID_FIELDER));
        lineup.add(createDeckPlayer(deck.getDeckCharacters().get(Position.SHORT_STOP), Position.SHORT_STOP));
        lineup.add(createDeckPlayer(deck.getDeckCharacters().get(Position.THIRD_BASE), Position.THIRD_BASE));
        return lineup;
    }

    private List<DeckPlayer> createBench(Deck deck) {
        List<DeckPlayer> bench = new ArrayList<>();
        bench.add(createDeckPlayer(deck.getDeckCharacters().get(Position.BENCH1), Position.BENCH1));
        bench.add(createDeckPlayer(deck.getDeckCharacters().get(Position.BENCH2), Position.BENCH2));
        bench.add(createDeckPlayer(deck.getDeckCharacters().get(Position.BENCH3), Position.BENCH3));
        return bench;
    }
}
