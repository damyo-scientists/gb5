package com.game.gb5.deck.util;

import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.utils.DeckMaker;
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

        ImportDeckDto importDeckDto = new ImportDeckDto(1L, 1L, 4L, 3L, 6L, 2L, 1L, 5L, 7L, 8L);
        Deck deck = deckMaker.toEntity(importDeckDto);
        assertNotNull(deck);
    }
}
