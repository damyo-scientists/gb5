package com.game.gb5.character.controller;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{character_id}")
    public ResponseEntity getById(@PathVariable("character_id") final long characterId) {
        Optional<GameCharacter> character = characterService.getById(characterId);
        if (character.isPresent()) {
            return new ResponseEntity<>(character.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
    }
}
