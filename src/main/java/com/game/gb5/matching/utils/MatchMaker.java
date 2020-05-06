package com.game.gb5.matching.utils;

import com.game.gb5.deck.service.DeckService;
import com.game.gb5.matching.dto.MatchingDto;
import com.game.gb5.matching.model.Matching;
import org.springframework.stereotype.Component;

@Component
public class MatchMaker {
    private DeckService deckService;

    public MatchMaker(DeckService deckService) {
        this.deckService = deckService;
    }

    public Matching toEntity(MatchingDto importMatchingDto) {
        Matching matching = new Matching();
        deckService.getByCode(importMatchingDto.getDeck1Code()).ifPresent(deck1 -> matching.setDeck1(deck1));
        deckService.getByCode(importMatchingDto.getDeck1Code()).ifPresent(deck2 -> matching.setDeck2(deck2));
        matching.setCode(importMatchingDto.getCode());
        return matching;
    }
}
