package com.game.gb5.repository.deck;

import com.game.gb5.model.deck.DeckCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckCharacterRepository extends JpaRepository<DeckCharacter, Long> {
    Optional<DeckCharacter> findByCode(String code);
}
