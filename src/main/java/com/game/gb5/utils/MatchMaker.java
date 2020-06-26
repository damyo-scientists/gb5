package com.game.gb5.utils;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.deck.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMaker {
    private DeckService deckService;

    @Autowired
    public MatchMaker(DeckService deckService) {
        this.deckService = deckService;
    }

    public Matching toEntity(MatchingDto importMatchingDto) {
        Matching matching = new Matching();
        matching.setId(importMatchingDto.getId());
        deckService.getByCode(importMatchingDto.getFirstDeckCode()).ifPresent(matching::setDeck1);
        deckService.getByCode(importMatchingDto.getFirstDeckCode()).ifPresent(matching::setDeck2);
        matching.setOpened(false);
        matching.setCode(importMatchingDto.getCode());
        return matching;
    }
}
