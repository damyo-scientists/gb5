package com.game.gb5.domain.voucher;


import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TicketList extends Voucher {
	@Column
	int resetSkillTicket;
	@Column
	int reinforceSkillTicket;
	@Column
	int trainingTicket;
	@Column
	int instantAcquisitionReportTicket; // (instantAcquisitionReportTicket)
	
	public boolean hasInstantAcquisitionReportTicket() {
		return instantAcquisitionReportTicket > 0;
	}
	
	public void consumeInstantAcquisitionReportTicket() {
		this.instantAcquisitionReportTicket -= 1;
	}
}

