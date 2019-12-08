package com.game.gb5.entity.voucher;


import lombok.Data;

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

