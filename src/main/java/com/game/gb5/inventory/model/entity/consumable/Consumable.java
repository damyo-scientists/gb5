package com.game.gb5.inventory.model.entity.consumable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.entity.BaseEntity;
import com.game.gb5.inventory.model.entity.inventory.Inventory;

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
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Inventory inventory;
}
