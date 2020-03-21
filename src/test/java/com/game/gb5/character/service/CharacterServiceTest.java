package com.game.gb5.character.service;

import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterServiceTest {
    @Autowired
    private CharacterService characterservice;

    @Test
    public void testCreateCharacter() {
        CharacterStatus characterStatus = new CharacterStatus(31, 68, 87, 85,
                90, 52, 57, 53, 44, 44, 72);

        GameCharacter gameCharacter =
                characterservice.createCharacter("하나", 2, 0,
                        0, 1, HittingPosition.LEFT, new ArrayList<>(),
                        characterStatus);
        System.out.println(gameCharacter);

    }
}
