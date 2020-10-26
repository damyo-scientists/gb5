package com.game.gb5.service.db;

import static com.game.gb5.service.db.DbDeck.TEST_DECK_1_CODE;
import static com.game.gb5.service.db.DbDeck.TEST_DECK_2_CDOE;
import static com.game.gb5.service.db.DbDeck.TEST_DECK_MAP_1;
import static com.game.gb5.service.db.DbDeck.TEST_DECK_MAP_2;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.service.character.CharacterService;
import com.game.gb5.service.deck.DeckService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbCreatorService {

  @Autowired
  private DeckService deckService;

  @Autowired
  private CharacterService characterService;

  public void initDatabase() {
    characterService.create(DbCharacter.HARU);
    characterService.create(DbCharacter.HANA);
    characterService.create(DbCharacter.YUJIN);
    characterService.create(DbCharacter.SUYEON);
    characterService.create(DbCharacter.SENA);
    characterService.create(DbCharacter.YUKI);
    characterService.create(DbCharacter.LIA);
    characterService.create(DbCharacter.LILITH);
    characterService.create(DbCharacter.MEGU);
    characterService.create(DbCharacter.EIONE);

    deckService.create(
        Deck.builder()
            .code(TEST_DECK_1_CODE)
            .deckCharacters(getDeckCharacters(TEST_DECK_MAP_1))
            .build());

    deckService.create(
        Deck.builder()
            .code(TEST_DECK_2_CDOE)
            .deckCharacters(getDeckCharacters(TEST_DECK_MAP_2))
            .build());
  }

  private Map<Position, DeckCharacter> getDeckCharacters(Map<Position, String> codeMap) {
    var deckCharacters = new HashMap<Position, DeckCharacter>();

    codeMap.forEach((position, code) -> {
      Character character = characterService.getByCode(code).orElseThrow();
      deckCharacters.put(position, DeckCharacter.builder().character(character).build());
    });
    return deckCharacters;
  }
}
