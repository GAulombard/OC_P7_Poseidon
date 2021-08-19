package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private static Logger LOGGER = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> findAll(){
        LOGGER.info("Process to find all bids");
        return bidListRepository.findAll();
    }

}
