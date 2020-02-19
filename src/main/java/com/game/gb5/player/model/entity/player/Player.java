package com.game.gb5.player.model.entity.player;

import com.game.gb5.entity.BaseEntity;
import com.game.gb5.entity.character.GameCharacter;
import com.game.gb5.inventory.model.entity.inventory.Inventory;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
}
