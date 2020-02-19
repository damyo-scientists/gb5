package com.game.gb5.inventory.model.entity.consumable;

import com.game.gb5.character.model.entity.GameCharacter;
import com.game.gb5.player.model.entity.player.Player;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CharacterPiece extends Consumable {
	@OneToOne
	private GameCharacter targetCharacter;
	@ManyToOne
	private Player player;
}
