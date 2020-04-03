package com.game.gb5.player.controller;

import com.game.gb5.player.model.Player;
import com.game.gb5.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestParam("user_name") String userName,
                                         @RequestParam("user_id") String userId) {
        Player player = playerService.create(userName, userId);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") final long id) {
        Player player = playerService.getById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Player> update(@RequestBody Player player) {
        Player existedPlayer = playerService.getById(player.getId());
        if (existedPlayer != null) {
            Player changedPlayer = playerService.update(player);
            return new ResponseEntity<>(changedPlayer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final long id) {
        Player player = playerService.getById(id);
        if (player != null) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
