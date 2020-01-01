package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class CharacterSet extends BaseEntity {
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private int cost;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "characterSetRelation", joinColumns = @JoinColumn(name = "character_set_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<GameCharacter> characters;
}
