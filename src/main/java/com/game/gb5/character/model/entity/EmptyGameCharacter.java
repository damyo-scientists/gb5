package com.game.gb5.character.model.entity;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class EmptyGameCharacter extends GameCharacter {
	private String name = "empty";
}