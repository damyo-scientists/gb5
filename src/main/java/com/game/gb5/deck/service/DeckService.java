package com.game.gb5.deck.service;

import com.game.gb5.deck.dto.DeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DeckService {
    private DeckRepository deckRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public Deck getById(Long id) {
        return this.deckRepository.getOne(id);
    }

    public Optional<Deck> getByCode(String code) {
        return this.deckRepository.findByCode(code);
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

    @Async
    public CompletableFuture<List<Deck>> importData(List<DeckDto> deckDtos) {
        List<Deck> decks = deckDtos.stream().map(dto -> {
            Optional<Deck> deckOptional = getByCode(dto.getCode());
            deckOptional.ifPresent(deck -> {
                dto.setId(deck.getId());
                dto.setCreatedDate(deck.getCreatedDate());
            });
            return dto.toEntity();
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(deckRepository.saveAll(decks));
    }
}
