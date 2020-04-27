package com.game.gb5.inventory.repository;

import com.game.gb5.inventory.model.item.voucher.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketListRepository extends JpaRepository<Ticket, Long> {
}
