package com.game.gb5.inventory.model.item;

import com.game.gb5.character.model.Character;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CharacterPiece extends Item {
	public CharacterPiece(Character targetCharacter) {
		this.targetCharacter = targetCharacter;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Character targetCharacter;
}
