package com.game.gb5.service.db;


import static org.assertj.core.api.Assertions.assertThat;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.service.deck.DeckService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DbDeckServiceTest {

  @Autowired
  private DbCreatorService dbCreatorService;

  @Autowired
  private DeckService deckService;

  @Test
  public void testInitDatabase() {
    dbCreatorService.initDatabase();

    Optional<Deck> deck1 = deckService.getByCode(DbDeck.TEST_DECK_1_CODE);
    assertThat(deck1).isPresent();
  }
}
