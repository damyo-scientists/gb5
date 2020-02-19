package com.game.gb5.inventory.repository;

import com.game.gb5.inventory.model.entity.voucher.TicketList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketListRepository extends JpaRepository<TicketList, Long> {
}
