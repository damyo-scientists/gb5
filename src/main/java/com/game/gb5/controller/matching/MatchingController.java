package com.game.gb5.controller.matching;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.game.GameService;
import com.game.gb5.service.matching.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/matchings")
@Tag(name = "Matchings")
@Slf4j
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matching> create(@RequestBody MatchingDto matchingDto, Errors errors) {
        if (!errors.hasErrors()) {
            log.info("hey : " + matchingDto.getFirstDeckCode());
            return new ResponseEntity<>(matchingService.create(matchingDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/{matching_id}/start")
    public ResponseEntity<GameResult> gameStart(@PathVariable("matching_id") final long matchId) {
        Optional<Matching> matchingOptional = matchingService.getById(matchId);
        if (matchingOptional.isPresent()) {
            Matching matching = matchingOptional.get();
            matchingService.starGame(matching, matching.getGame());
            return new ResponseEntity<>(gameService.startGame(matching), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
