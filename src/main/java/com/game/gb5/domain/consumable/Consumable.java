package com.game.gb5.domain.consumable;

import com.game.gb5.domain.BaseEntity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Consumable extends BaseEntity {
	@Column
	private int count;
	@Column
	private String name;
}
