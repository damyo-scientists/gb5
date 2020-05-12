package com.game.gb5.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.character.CharacterReportStatus;
import com.game.gb5.model.character.Character;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class ReportCharacter extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private ScoutingReport scoutingReport;
	@OneToOne
	private Character targetCharacter;
	@Transient
	private CharacterReportStatus characterReportStatus;
}
