package com.game.gb5.domain.voucher;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TicketList extends Voucher {
	int resetSkillTicket;
	int reinforceSkillTicket;
	int trainingTicket;
	int instantAcquisitionReportTicket; // (instantAcquisitionReportTicket)
	
	public boolean hasInstantAcquisitionReportTicket() {
		return instantAcquisitionReportTicket > 0;
	}
	
	public void consumeInstantAcquisitionReportTicket() {
		this.instantAcquisitionReportTicket -= 1;
	}
}

