package com.game.gb5.scouter.controller;

import com.game.gb5.scouter.model.Scouter;
import com.game.gb5.scouter.service.ScoutingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scouter")
public class ScouterController {
	@Autowired
	private ScoutingService scoutingService;
	
	@GetMapping("/{id}")
	public ResponseEntity getScouterById(@PathVariable("id") final long id) {
		Scouter scouter = scoutingService.getScouterById(id);
		if (scouter != null) {
			return new ResponseEntity<>(scouter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Scouter not found", HttpStatus.NOT_FOUND);
		}
	}
}
