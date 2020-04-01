package com.game.gb5.deck.contrller;

import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deck")
public class DeckController {
    private final static Logger loger = LoggerFactory.getLogger(DeckController.class);

    private DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public ResponseEntity<Deck> create(@RequestBody Deck deck) {
        deckService.create(deck);
        return new ResponseEntity<>(deck, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getById(@PathVariable("id") Long id) {
        Deck deck = deckService.getById(id);
        if (deck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deck, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Deck> update(@RequestBody Deck deck) {
        Deck oldDeck = deckService.getById(deck.getId());
        if (oldDeck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Deck newDeck = deckService.update(deck);
        return new ResponseEntity<>(newDeck, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Deck deck = deckService.getById(id);
        if (deck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deckService.delete(deck);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
