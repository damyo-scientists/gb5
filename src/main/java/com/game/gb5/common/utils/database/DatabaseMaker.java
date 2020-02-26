package com.game.gb5.common.utils.database;

import com.game.gb5.character.model.entity.GameCharacterDao;
import com.game.gb5.player.repository.PlayerRepository;
import com.game.gb5.scouter.repository.ScoutingReportDao;
import com.game.gb5.inventory.repository.TicketListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseMaker implements CommandLineRunner {
	@Autowired
	private GameCharacterDao gameCharacterDao;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TicketListRepository ticketListRepository;
	
	@Autowired
	private ScoutingReportDao scoutingReportDao;
	
	@Override
	public void run(String... args) throws Exception {
//		Player player = playerDao.getPlayerById(1L);
//		Ticket tickets = new Ticket();
//		tickets.setInstantAcquisitionReportTicket(9999999);
//		ticketListDao.save(tickets);
//		player.setTickets(tickets);
//		playerDao.save(player);
	}
}
