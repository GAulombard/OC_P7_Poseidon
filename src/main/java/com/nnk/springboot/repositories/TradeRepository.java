package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Trade repository.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
