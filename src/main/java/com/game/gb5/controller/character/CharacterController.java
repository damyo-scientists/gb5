package com.game.gb5.controller.character;

import com.game.gb5.dto.CharacterDto;
import com.game.gb5.model.character.Character;
import com.game.gb5.service.character.CharacterService;
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
    public ResponseEntity<Character> getById(@PathVariable("character_id") final long characterId) {
        Optional<Character> character = characterService.getById(characterId);
        return character.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Character> create(@Valid @RequestBody CharacterDto characterDto, Errors errors) {
        if (!errors.hasErrors()) {
            Character character = characterService.create(characterDto);
            if (character == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            URI uri = linkTo(CharacterController.class).slash(character.getId()).toUri();
            return ResponseEntity.created(uri).body(character);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SneakyThrows
    @Transactional
    @PutMapping("/import-data")
    public ResponseEntity<List<Character>> importData(@Valid @RequestBody List<CharacterDto> characterDtos, Errors errors) {
        if (!errors.hasErrors()) {
            List<Character> characters = characterService.importData(characterDtos).get();
            if (characters == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(characters);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
