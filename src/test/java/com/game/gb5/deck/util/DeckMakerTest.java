package com.game.gb5.deck.util;

import com.game.gb5.dto.ImportDeckDto;
import com.game.gb5.model.Deck;
import com.game.gb5.utils.DeckMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckMakerTest {
    @Autowired
    private DeckMaker deckMaker;

    @Test
    public void testToEntity() {
        assertNotNull(deckMaker);

        ImportDeckDto importDeckDto = new ImportDeckDto(1L, 1L, "test-code1", "test-code3", "test-code6", "test-code2", "test-code1", "test-code5", "test-code7", "test-code8");
        Deck deck = deckMaker.toEntity(importDeckDto);
        assertNotNull(deck);
    }
}
