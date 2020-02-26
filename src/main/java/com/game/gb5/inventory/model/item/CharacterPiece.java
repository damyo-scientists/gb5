package com.game.gb5.inventory.model.item;

import com.game.gb5.character.model.GameCharacter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CharacterPiece extends Item {
	public CharacterPiece(GameCharacter targetCharacter) {
		this.targetCharacter = targetCharacter;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	private GameCharacter targetCharacter;
}
