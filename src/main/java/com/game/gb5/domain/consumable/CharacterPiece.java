package com.game.gb5.domain.consumable;

import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.player.Player;

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
