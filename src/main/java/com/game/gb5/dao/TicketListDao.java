package com.game.gb5.dao;

import com.game.gb5.domain.voucher.TicketList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketListDao extends CrudRepository<TicketList, Long> {
}
