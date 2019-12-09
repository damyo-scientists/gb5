package com.game.gb5.domain.character;

import java.util.List;

import lombok.Data;

@Data
public class CharacterSet {
	private String code;
	private String name;
	private List<Character> targetCharacters;
	private int cost;
}
