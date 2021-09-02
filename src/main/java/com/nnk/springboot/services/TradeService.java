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

@Service
public class TradeService {

    private static Logger LOGGER = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> findAll() {
        LOGGER.info("Process to find all trade");

        return tradeRepository.findAll();
    }

    public void save(Trade trade) {
        LOGGER.info("Process to save new trade");

        tradeRepository.save(trade);
    }

    public Trade findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find trade by id");

        if(!tradeRepository.existsById(id)) throw new NotFoundException("trade not found");

        return tradeRepository.getOne(id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete trade by id");

        if(!tradeRepository.existsById(id)) throw new NotFoundException("trade not found");

        tradeRepository.deleteById(id);;

    }
}
