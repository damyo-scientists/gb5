package com.game.gb5.deck.service;

import com.game.gb5.character.dto.CharacterDto;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.character.service.CharacterService;
import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckServiceTest {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private DeckService deckService;

    @Test
    @Transactional
    public void testImportData() {
        CharacterStatus characterStatus1 = new CharacterStatus(31, 68, 87, 85,
                90, 52, 57, 53, 44, 44, 72);

        CharacterDto gameCharacter1 = new CharacterDto(1L, "test-code1", "하나", 2, 0,
                0, 1, HittingPosition.LEFT, new ArrayList<>(),
                characterStatus1);

        CharacterStatus characterStatus2 = new CharacterStatus(32, 69, 88, 86,
                91, 53, 58, 54, 45, 45, 73);

        CharacterDto gameCharacter2 =
                new CharacterDto(2L, "test-code2", "둘", 1, 0,
                        0, 1, HittingPosition.RIGHT, new ArrayList<>(),
                        characterStatus2);
        List<CharacterDto> characterList = new ArrayList<>();
        characterList.add(gameCharacter1);
        characterList.add(gameCharacter2);
        characterService.importData(characterList);

        ImportDeckDto importDeckDto = new ImportDeckDto(1L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<ImportDeckDto> deckDtos = new ArrayList<>();
        deckDtos.add(importDeckDto);

        deckService.importData(deckDtos);

        Optional<Deck> deck = deckService.getByCode("test-deck1");
        Assert.assertTrue(deck.isPresent());
        Assert.assertEquals("하나", deck.get().getChracters().get(Position.FIRST_BASE).getName());
    }
}
