package com.game.gb5.controller;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matching")
public class MatchingController {
    @Autowired
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

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
    public ResponseEntity<Matching> update(@RequestBody MatchingDto matchingDto) {
        Optional<Matching> matching = matchingService.getById(matchingDto.getId());
        if (matching.isPresent()) {
            // todo check
            return new ResponseEntity<>(matchingService.update(matchingDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
