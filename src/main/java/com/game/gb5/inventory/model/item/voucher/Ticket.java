package com.game.gb5.inventory.model.item.voucher;


import com.game.gb5.inventory.model.item.TicketType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ticket extends Voucher {
	
	@Enumerated(EnumType.STRING)
	private TicketType type;
}

