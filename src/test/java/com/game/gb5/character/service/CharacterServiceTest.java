package com.game.gb5.character.service;

import com.game.gb5.character.dto.CharacterDto;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
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
public class CharacterServiceTest {
    @Autowired
    private CharacterService characterservice;

    @Test
    public void testGetById() {
        CharacterStatus characterStatus = new CharacterStatus(31, 68, 87, 85,
                90, 52, 57, 53, 44, 44, 72);

        GameCharacter gameCharacter =
                characterservice.create("하나", 2, 0,
                        0, 1, HittingPosition.LEFT, new ArrayList<>(),
                        characterStatus);
        Optional<GameCharacter> gameCharacterOptional = characterservice.getById(gameCharacter.getId());
        Assert.assertTrue(gameCharacterOptional.isPresent());
    }

    @Test
    @Transactional
    public void testCreate() {
        CharacterStatus characterStatus = new CharacterStatus(31, 68, 87, 85,
                90, 52, 57, 53, 44, 44, 72);

        GameCharacter gameCharacter =
                characterservice.create("하나", 2, 0,
                        0, 1, HittingPosition.LEFT, new ArrayList<>(),
                        characterStatus);
        Assert.assertEquals("하나", gameCharacter.getName());
    }

    @Test
    @Transactional
    public void testImportData() {
        CharacterStatus characterStatus1 = new CharacterStatus(31, 68, 87, 85,
                90, 52, 57, 53, 44, 44, 72);

        CharacterDto gameCharacter1 = new CharacterDto(0L, "test-code1", "하나", 2, 0,
                0, 1, HittingPosition.LEFT, new ArrayList<>(),
                characterStatus1);

        CharacterStatus characterStatus2 = new CharacterStatus(32, 69, 88, 86,
                91, 53, 58, 54, 45, 45, 73);

        CharacterDto gameCharacter2 =
                new CharacterDto(0L, "test-code2", "둘", 1, 0,
                        0, 1, HittingPosition.RIGHT, new ArrayList<>(),
                        characterStatus2);
        List<CharacterDto> characters = new ArrayList<>();
        characters.add(gameCharacter1);
        characters.add(gameCharacter2);
        characterservice.importData(characters);

        Optional<GameCharacter> savedCharacter1 = characterservice.getByCode("test-code1");
        Assert.assertTrue(savedCharacter1.isPresent());

        Optional<GameCharacter> notSavedCharacter1 = characterservice.getByCode("test-code11");
        Assert.assertFalse(notSavedCharacter1.isPresent());

        Optional<GameCharacter> savedCharacter2 = characterservice.getByCode("test-code2");
        Assert.assertTrue(savedCharacter2.isPresent());

        CharacterDto updatedGameCharacter1 = new CharacterDto(0L, "test-code1", "하나", 3, 0,
                0, 1, HittingPosition.LEFT, new ArrayList<>(),
                characterStatus1);

        List<CharacterDto> updateCharacters = new ArrayList<>();
        characters.add(updatedGameCharacter1);

        characterservice.importData(updateCharacters);

        Optional<GameCharacter> updatedCharacter1 = characterservice.getByCode("test-code1");
        Assert.assertTrue(updatedCharacter1.isPresent());
        Assert.assertEquals(3, updatedCharacter1.get().getGrade());
    }
}
