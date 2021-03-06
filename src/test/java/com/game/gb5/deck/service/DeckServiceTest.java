package com.game.gb5.deck.service;

import com.game.gb5.character.dto.CharacterDto;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.character.service.CharacterService;
import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.model.Position;
import com.game.gb5.player.model.Player;
import com.game.gb5.player.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckServiceTest {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private PlayerService playerService;

    @Test
    public void testImportData() throws ExecutionException, InterruptedException {
        Player player = playerService.create("test_player", "test player");

        List<CharacterDto> characterDtoList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            CharacterStatus characterStatus = new CharacterStatus(31, 68, 87, 85,
                    90, 52, 57, 53, 44, 44, 72);

            CharacterDto characterDto = new CharacterDto(0L, "test-code" + i, "test character " + i,
                    2, 0, 0, i, HittingPosition.LEFT, new ArrayList<>(), characterStatus);
            characterDtoList.add(characterDto);
        }

        characterService.importData(characterDtoList).get();

        ImportDeckDto importDeckDto = new ImportDeckDto(null, player.getId(), "test-code1", "test-code2", "test-code3", "test-code4", "test-code5", "test-code6", "test-code7", "test-code8");
        String deckCode = "test-deck";
        importDeckDto.setCode(deckCode);
        List<ImportDeckDto> deckDtos = new ArrayList<>();
        deckDtos.add(importDeckDto);
        deckService.importData(deckDtos).get();

        Optional<Deck> deck = deckService.getByCode(deckCode);
        Assert.assertTrue(deck.isPresent());

        Assert.assertEquals("test character 1", deck.get().getCharacters().get(Position.FIRST_BASE).getName());
    }
}
