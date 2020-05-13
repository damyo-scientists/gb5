package com.game.gb5.utils;

import com.game.gb5.dto.ImportDeckDto;
import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.player.Player;
import com.game.gb5.service.character.CharacterService;
import com.game.gb5.service.deck.DeckCharacterService;
import com.game.gb5.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeckMaker {
    private final CharacterService characterService;
    private final PlayerService playerService;
    private final DeckCharacterService deckCharacterService;

    @Autowired
    public DeckMaker(CharacterService characterService, PlayerService playerService, DeckCharacterService deckCharacterService) {
        this.characterService = characterService;
        this.playerService = playerService;
        this.deckCharacterService = deckCharacterService;
    }

    public Deck toEntity(ImportDeckDto importDeckDto) {
        Map<Position, DeckCharacter> characters = new HashMap<>();
        characterService.getByCode(importDeckDto.getFirstBaseCode()).ifPresent(chr -> characters.put(Position.FIRST_BASE, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getSecondBaseCode()).ifPresent(chr -> characters.put(Position.SECOND_BASE, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getThirdBaseCode()).ifPresent(chr -> characters.put(Position.THIRD_BASE, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getShortStopCode()).ifPresent(chr -> characters.put(Position.SHORT_STOP, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getMidFielderCode()).ifPresent(chr -> characters.put(Position.MID_FIELDER, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getBench1Code()).ifPresent(chr -> characters.put(Position.BENCH1, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getBench2Code()).ifPresent(chr -> characters.put(Position.BENCH2, deckCharacterService.create(chr)));
        characterService.getByCode(importDeckDto.getBench3Code()).ifPresent(chr -> characters.put(Position.BENCH3, deckCharacterService.create(chr)));

        Player player = playerService.getById(importDeckDto.getPlayerId());

        return new Deck(importDeckDto.getId(), importDeckDto.getCode(), characters, player);
    }
}
