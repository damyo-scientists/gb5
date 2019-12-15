package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CharacterSet extends BaseEntity {
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private int cost;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "characterSetRelation")
	private List<GameCharacter> characters;
}
