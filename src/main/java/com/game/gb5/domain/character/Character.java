package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Character extends BaseEntity {
	@Column
	private String name;
	@Column
	private int rate;
	@Column
	private int acquisitionCoefficient;
	@Column
	private int cumulativeAcquisitionCoefficient;
	@Column
	private int level;
	@Column
	private int exp;
	@Column
	private int backNumber;
	@OneToOne
	private CharacterStatus characterStatus;
	@ManyToOne
	private CharacterSet characterSet;
	private CharacterStatusReport characterStatusReport;
}
