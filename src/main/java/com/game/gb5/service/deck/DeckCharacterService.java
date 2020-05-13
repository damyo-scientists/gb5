package com.game.gb5.service.deck;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.repository.deck.DeckCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckCharacterService {
    private DeckCharacterRepository deckCharacterRepository;

    @Autowired
    DeckCharacterService(DeckCharacterRepository deckCharacterRepository) {
        this.deckCharacterRepository = deckCharacterRepository;
    }

    public DeckCharacter create(Character character) {
        DeckCharacter deckCharacter = DeckCharacter.builder().character(character).build();
        return this.deckCharacterRepository.save(deckCharacter);
    }
}
