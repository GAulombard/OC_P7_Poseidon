package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Trade service.
 */
@Service
public class TradeService {

    private static Logger LOGGER = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Trade> findAll() {
        LOGGER.info("Process to find all trade");

        return tradeRepository.findAll();
    }

    /**
     * Save.
     *
     * @param trade the trade
     */
    public void save(Trade trade) {
        LOGGER.info("Process to save new trade");

        tradeRepository.save(trade);
    }

    /**
     * Find by id trade.
     *
     * @param id the id
     * @return the trade
     * @throws NotFoundException the not found exception
     */
    public Trade findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find trade by id");

        if(!tradeRepository.existsById(id)) throw new NotFoundException("trade not found");

        return tradeRepository.getOne(id);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws NotFoundException the not found exception
     */
    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete trade by id");

        if(!tradeRepository.existsById(id)) throw new NotFoundException("trade not found");

        tradeRepository.deleteById(id);;

    }
}
