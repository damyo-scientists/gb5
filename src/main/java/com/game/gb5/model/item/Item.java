package com.game.gb5.model.item;

import com.game.gb5.model.BaseEntity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item extends BaseEntity {
	@Column
	private String name;
}
