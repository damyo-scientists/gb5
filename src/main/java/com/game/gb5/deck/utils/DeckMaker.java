package com.game.gb5.deck.utils;

import com.game.gb5.character.model.Character;
import com.game.gb5.character.service.CharacterService;
import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.model.Position;
import com.game.gb5.player.model.Player;
import com.game.gb5.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeckMaker {
    @Autowired
    public DeckMaker(CharacterService characterService, PlayerService playerService) {
        this.characterService = characterService;
        this.playerService = playerService;
    }

    private final CharacterService characterService;
    private final PlayerService playerService;

    public Deck toEntity(ImportDeckDto importDeckDto) {
        Map<Position, Character> characters = new HashMap<>();
        characterService.getById(importDeckDto.getFirstBaseId()).ifPresent(chr -> characters.put(Position.FIRST_BASE, chr));
        characterService.getById(importDeckDto.getSecondBaseId()).ifPresent(chr -> characters.put(Position.SECOND_BASE, chr));
        characterService.getById(importDeckDto.getThirdBaseId()).ifPresent(chr -> characters.put(Position.THIRD_BASE, chr));
        characterService.getById(importDeckDto.getShortStopId()).ifPresent(chr -> characters.put(Position.SHORT_STOP, chr));
        characterService.getById(importDeckDto.getMidFielderId()).ifPresent(chr -> characters.put(Position.MID_FIELDER, chr));

        Player player = playerService.getById(importDeckDto.getPlayerId());

        return new Deck(null, importDeckDto.getCode(), characters, player);
    }
}
