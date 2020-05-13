package com.game.gb5.repository.inventory;

import com.game.gb5.model.item.voucher.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketListRepository extends JpaRepository<Ticket, Long> {
}
