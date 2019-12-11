package com.game.gb5.domain.character;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class EmptyCharacter extends Character {
	private String name = "empty";
}
