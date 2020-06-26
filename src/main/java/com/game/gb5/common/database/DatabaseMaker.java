package com.game.gb5.common.database;

import com.game.gb5.repository.character.CharacterRepository;
import com.game.gb5.repository.inventory.TicketListRepository;
import com.game.gb5.repository.user.UserRepository;
import com.game.gb5.repository.scouting.ScoutingReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseMaker implements CommandLineRunner {
    @Autowired
    private CharacterRepository gameCharacterDao;

    @Autowired
    private UserRepository userRepository;

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
