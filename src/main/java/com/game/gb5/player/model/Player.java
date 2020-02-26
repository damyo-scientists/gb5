package com.game.gb5.player.model;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.deck.model.Position;
import com.game.gb5.inventory.model.inventory.Inventory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Player extends BaseEntity {
	public Player(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
		this.inventory = new Inventory(this);
	}
	
	@Column
	private String userId;
	@Column
	private String userName;
	@Column
	private Date reportAcquisitionResetTime;
	@OneToOne(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
	private Inventory inventory;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "playerCharacterRelation")
	private List<GameCharacter> characters;
	
	@ElementCollection
	@MapKeyColumn(name = "position")
	@Column(name = "character")
	private Map<Position, GameCharacter> memberDeck;
}
