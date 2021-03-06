package com.game.gb5.scouter.model;

import com.game.gb5.common.model.BaseEntity;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class ScouterStatus extends BaseEntity {
	@Column
	private int deflection;
	@Column
	private int deflectionRandomizeValue;
	@Column
	private int reportChracterSize;
	@Column(columnDefinition = "varchar(8)")
	private LocalTime reportResetTime;
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
