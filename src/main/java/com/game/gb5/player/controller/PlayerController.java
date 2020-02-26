package com.game.gb5.player.controller;

import com.game.gb5.player.model.Player;
import com.game.gb5.player.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
	private final PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping
	public ResponseEntity createPlayer(@RequestParam("user_name") String userName,
	                                   @RequestParam("user_id") String userId) {
		Player player = playerService.createPlayer(userName, userId);
		if (player != null) {
			return new ResponseEntity<>(player, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Player is empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
