package com.game.gb5.controller.matching;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.game.GameService;
import com.game.gb5.service.matching.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matching")
public class MatchingController {
    @Autowired
    public MatchingController(MatchingService matchingService, GameService gameService) {
        this.matchingService = matchingService;
        this.gameService = gameService;
    }

    private GameService gameService;
    private MatchingService matchingService;

    @GetMapping("/{matching_id}")
    public ResponseEntity<Matching> getById(@PathVariable("matching_id") final long matchId) {
        Optional<Matching> character = matchingService.getById(matchId);
        return character.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Matching> create(@RequestBody MatchingDto matchingDto) {
        return new ResponseEntity<>(matchingService.create(matchingDto), HttpStatus.OK);
    }

    @PutMapping("/{matching_id}")
    public ResponseEntity<Matching> playGame(@RequestBody MatchingDto matchingDto) {
        Optional<Matching> matchingOptional = matchingService.getById(matchingDto.getId());
        if (matchingOptional.isPresent()) {
            Matching matching = matchingOptional.get();
            Game game = gameService.create(matching);
            return new ResponseEntity<>(matchingService.update(matching, game), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
