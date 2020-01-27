package com.game.gb5.domain.scouting.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.scouting.Scouter;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@ToString(callSuper = true)
public class ScoutingReport extends BaseEntity {
	@Column
	private Date baseTime = new Date();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ReportCharacter> reportCharacterList;
	@JsonIgnore
	@OneToOne(mappedBy = "scoutingReport", cascade = CascadeType.ALL)
	private Scouter scouter;
}
