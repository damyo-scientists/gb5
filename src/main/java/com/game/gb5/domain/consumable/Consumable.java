package com.game.gb5.domain.consumable;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.player.Inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Consumable extends BaseEntity {
	@Column
	private int count;
	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Inventory inventory;
}
