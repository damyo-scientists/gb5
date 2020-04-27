package com.game.gb5.deck.repository;

import com.game.gb5.deck.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    Optional<Deck> findByCode(String code);
}
