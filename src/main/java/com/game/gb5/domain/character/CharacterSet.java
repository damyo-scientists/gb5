package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class CharacterSet extends BaseEntity {
	@Column
	private String code;
	@Column
	private String name;
	@OneToMany(mappedBy = "messageModel", cascade = {CascadeType.ALL})
	private List<Character> targetCharacters;
	@Column
	private int cost;
}
