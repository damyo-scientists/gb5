package com.game.gb5.utils.database;

import com.game.gb5.character.model.entity.GameCharacterDao;
import com.game.gb5.player.repository.PlayerRepository;
import com.game.gb5.dao.ScoutingReportDao;
import com.game.gb5.dao.TicketListDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseMaker implements CommandLineRunner {
	@Autowired
	private GameCharacterDao gameCharacterDao;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TicketListDao ticketListDao;
	
	@Autowired
	private ScoutingReportDao scoutingReportDao;
	
	@Override
	public void run(String... args) throws Exception {
//		Player player = playerDao.getPlayerById(1L);
//		TicketList ticketList = new TicketList();
//		ticketList.setInstantAcquisitionReportTicket(9999999);
//		ticketListDao.save(ticketList);
//		player.setTicketList(ticketList);
//		playerDao.save(player);
	}
}
