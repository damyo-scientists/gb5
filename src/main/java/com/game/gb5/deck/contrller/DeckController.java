package com.game.gb5.deck.contrller;

import com.game.gb5.deck.dto.DeckDto;
import com.game.gb5.deck.dto.ImportDeckDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/decks")
public class DeckController {
    private final static Logger logger = LoggerFactory.getLogger(DeckController.class);
    private DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deck> create(@Valid DeckDto deckDto, Errors errors) {
        if (!errors.hasErrors()) {
            Deck deck = deckService.create(deckDto);
            return new ResponseEntity<>(deck, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    @PutMapping("/import-data")
    public ResponseEntity<List<Deck>> importData(@Valid @RequestBody ImportDeckDto[] importDeckDtos, Errors errors) throws ExecutionException, InterruptedException {
        if (!errors.hasErrors()) {
            List<Deck> decks = deckService.importData(Arrays.asList(importDeckDtos)).get();
            return new ResponseEntity<>(decks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getById(@PathVariable("id") Long id) {
        Optional<Deck> deck = deckService.getById(id);
        if (deck.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deck.get(), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Deck> update(@RequestBody Deck deck) {
        Optional<Deck> oldDeck = deckService.getById(deck.getId());
        if (oldDeck.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Deck newDeck = deckService.update(deck);
        return new ResponseEntity<>(newDeck, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Deck> deck = deckService.getById(id);
        if (deck.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deckService.delete(deck.get());
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
