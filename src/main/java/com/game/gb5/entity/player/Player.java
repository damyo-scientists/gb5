package com.game.gb5.entity.player;


import com.game.gb5.entity.voucher.PassList;
import com.game.gb5.entity.voucher.TicketList;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Player {
	private String id;
	private int money;
	private int star;
	private List<Character> characterList;
	private String userName;
	private Date reportAcquisitionResetTime;
	private TicketList ticketList;
	private PassList passList;
}
