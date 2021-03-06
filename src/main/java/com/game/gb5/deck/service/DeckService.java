package com.game.gb5.deck.service;

import com.game.gb5.deck.dto.DeckDto;
import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.repository.DeckRepository;
import com.game.gb5.deck.utils.DeckMaker;
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
    private DeckMaker deckMaker;

    @Autowired
    public DeckService(DeckRepository deckRepository, DeckMaker deckMaker) {
        this.deckRepository = deckRepository;
        this.deckMaker = deckMaker;
    }

    public Optional<Deck> getById(Long id) {
        return this.deckRepository.findById(id);
    }

    public Optional<Deck> getByCode(String code) {
        return this.deckRepository.findByCode(code);
    }

    public Deck create(DeckDto deckDto) {
        return this.deckRepository.save(deckDto.toEntity());
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
    public CompletableFuture<List<Deck>> importData(List<ImportDeckDto> importDeckDtos) {
        List<Deck> decks = importDeckDtos.stream().map(dto -> {
            Optional<Deck> deckExisted = getByCode(dto.getCode());
            deckExisted.ifPresent(deckPresent -> {
                dto.setId(deckPresent.getId());
                dto.setCreatedDate(deckPresent.getCreatedDate());
            });
            Deck deckEntity = deckMaker.toEntity(dto);
            if (dto.getCreatedDate() != null) {
                deckEntity.setCreatedDate(dto.getCreatedDate());
            }
            return deckEntity;
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(deckRepository.saveAll(decks));
    }
}
