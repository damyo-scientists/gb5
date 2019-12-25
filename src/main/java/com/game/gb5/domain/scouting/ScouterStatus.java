package com.game.gb5.domain.scouting;

import com.game.gb5.domain.BaseEntity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ScouterStatus extends BaseEntity {
	@Column
	private int deflection;
	@Column
	private int deflectionRandomizeValue;
	@Column
	private int reportChracterSize;
	@Column
	private Date reportResetTime;
	@Column
	private String reportRandomCount;
	@ElementCollection
	@Column
	private List<Integer> gradeAcquisitionRates;
	@ElementCollection
	@Column
	private List<Integer> reportHideStatusColumns;
	@Column
	private boolean isGiveMinimumGradeAssured;
}
