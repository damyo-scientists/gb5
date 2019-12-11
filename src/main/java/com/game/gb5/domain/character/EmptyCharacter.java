package com.game.gb5.domain.character;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class EmptyCharacter extends Character {
	private String name = "empty";
}
