package com.game.gb5.simulation.system;

import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.service.deck.DeckCharacterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BattingSystemTest {
    @Autowired
    private BattingSystem battingSystem;

    @Autowired
    private DeckCharacterService deckCharacterService;

    @Test
    public void testPlayBattingTest() {
        Optional<DeckCharacter> deckCharacterOptional = deckCharacterService.getByCode("test-1");
        Assert.assertTrue(deckCharacterOptional.isPresent());

        DeckCharacter deckCharacter = deckCharacterOptional.get();
        DeckPlayer batter = DeckPlayer.builder().deckCharacter(deckCharacter).deckStatus(deckCharacter.getCharacter().getCharacterStatus()).build();
//        battingSystem.hitAndRun(deckCharacter);


    }
}
