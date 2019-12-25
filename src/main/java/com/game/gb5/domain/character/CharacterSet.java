package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CharacterSet extends BaseEntity {
	private static final long serialVersionUID = 5587224900064325810L;
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private int cost;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "characterSetRelation", joinColumns = @JoinColumn(name = "character_set_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<GameCharacter> characters;
}
