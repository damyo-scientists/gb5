package com.game.gb5.character.controller;

import com.game.gb5.character.dto.CharacterDto;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.service.CharacterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{character_id}")
    public ResponseEntity<GameCharacter> getById(@PathVariable("character_id") final long characterId) {
        Optional<GameCharacter> character = characterService.getById(characterId);
        if (character.isPresent()) {

            return new ResponseEntity<>(character.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<GameCharacter> create(@Valid @RequestBody CharacterDto characterDto, Errors errors) {
        if (!errors.hasErrors()) {
            GameCharacter gameCharacter = characterService.create(characterDto);
            if (gameCharacter == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            URI uri = linkTo(CharacterController.class).slash(gameCharacter.getId()).toUri();
            return ResponseEntity.created(uri).body(gameCharacter);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SneakyThrows
    @Transactional
    @PutMapping("/import-data")
    public ResponseEntity<List<GameCharacter>> importData(@Valid @RequestBody List<CharacterDto> characterDtos, Errors errors) {
        if (!errors.hasErrors()) {
            List<GameCharacter> gameCharacters = characterService.importData(characterDtos).get();
            if (gameCharacters == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(gameCharacters);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
