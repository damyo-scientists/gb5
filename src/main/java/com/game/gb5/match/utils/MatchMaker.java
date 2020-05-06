package com.game.gb5.match.utils;

import com.game.gb5.deck.service.DeckService;
import com.game.gb5.match.dto.MatchDto;
import com.game.gb5.match.model.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMaker {
    private DeckService deckService;

    public MatchMaker(DeckService deckService) {
        this.deckService = deckService;
    }

    public Match toEntity(MatchDto importMatchDto) {
        Match match = new Match();
        deckService.getByCode(importMatchDto.getDeck1Code()).ifPresent(deck1 -> match.setDeck1(deck1));
        deckService.getByCode(importMatchDto.getDeck1Code()).ifPresent(deck2 -> match.setDeck2(deck2));
        return match;
    }
}
