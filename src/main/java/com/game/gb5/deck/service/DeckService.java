package com.game.gb5.deck.service;

import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {
    private DeckRepository deckRepository;

    @Autowired
    DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public Deck getById(Long id) {
        return this.deckRepository.getOne(id);
    }

    public Deck create(Deck deck) {
        return this.deckRepository.save(deck);
    }

    public List<Deck> createAll(List<Deck> decks) {
        return this.deckRepository.saveAll(decks);
    }

    public Deck update(Deck deck) {
        return this.deckRepository.save(deck);
    }

    public void delete(Deck deck) {
        this.deckRepository.delete(deck);
    }
}
