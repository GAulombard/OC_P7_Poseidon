package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
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
        LOGGER.info("Process to find all existing bids");

        return bidListRepository.findAll();
    }

    public void save(BidList bid) {
        LOGGER.info("Process to save a bid in database");

        bidListRepository.save(bid);
    }

    public BidList findBidById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find a bid by Id");

        if(!bidListRepository.existsById(id)) throw new NotFoundException("Bid not found");

        BidList bid = bidListRepository.getOne(id);
        return bid;

    }

    public void deleteBidById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete a bid by Id");

        if(!bidListRepository.existsById(id)) throw new NotFoundException("Bid not found");

        bidListRepository.deleteById(id);
    }

}
