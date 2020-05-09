package com.game.gb5.utils;

import com.game.gb5.model.Character;
import com.game.gb5.service.CharacterService;
import com.game.gb5.dto.ImportDeckDto;
import com.game.gb5.model.Deck;
import com.game.gb5.model.Position;
import com.game.gb5.model.Player;
import com.game.gb5.service.PlayerService;
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
        characterService.getByCode(importDeckDto.getFirstBaseCode()).ifPresent(chr -> characters.put(Position.FIRST_BASE, chr));
        characterService.getByCode(importDeckDto.getSecondBaseCode()).ifPresent(chr -> characters.put(Position.SECOND_BASE, chr));
        characterService.getByCode(importDeckDto.getThirdBaseCode()).ifPresent(chr -> characters.put(Position.THIRD_BASE, chr));
        characterService.getByCode(importDeckDto.getShortStopCode()).ifPresent(chr -> characters.put(Position.SHORT_STOP, chr));
        characterService.getByCode(importDeckDto.getMidFielderCode()).ifPresent(chr -> characters.put(Position.MID_FIELDER, chr));
        characterService.getByCode(importDeckDto.getBench1Code()).ifPresent(chr -> characters.put(Position.BENCH1, chr));
        characterService.getByCode(importDeckDto.getBench2Code()).ifPresent(chr -> characters.put(Position.BENCH2, chr));
        characterService.getByCode(importDeckDto.getBench3Code()).ifPresent(chr -> characters.put(Position.BENCH3, chr));

        Player player = playerService.getById(importDeckDto.getPlayerId());

        return new Deck(importDeckDto.getId(), importDeckDto.getCode(), characters, player);
    }
}
