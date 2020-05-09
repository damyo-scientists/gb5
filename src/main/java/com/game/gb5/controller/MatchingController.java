package com.game.gb5.controller;

import com.game.gb5.model.Matching;
import com.game.gb5.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/matching")
public class MatchingController {
    @Autowired
    private MatchingService matchingService;

    @GetMapping("/{match_id}")
    public ResponseEntity<Matching> getById(@PathVariable("match_id") final long matchId) {
        Optional<Matching> character = matchingService.getById(matchId);
        return character.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
