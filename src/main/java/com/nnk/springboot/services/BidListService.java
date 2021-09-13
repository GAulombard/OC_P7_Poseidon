package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Bid list service.
 */
@Service
public class BidListService {

    private static Logger LOGGER = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<BidList> findAll(){
        LOGGER.info("Process to find all existing bids");

        return bidListRepository.findAll();
    }

    /**
     * Save.
     *
     * @param bid the bid
     */
    public void save(BidList bid) {
        LOGGER.info("Process to save a bid in database");

        bidListRepository.save(bid);
    }

    /**
     * Find bid by id bid list.
     *
     * @param id the id
     * @return the bid list
     * @throws NotFoundException the not found exception
     */
    public BidList findBidById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find a bid by Id");

        if(!bidListRepository.existsById(id)) throw new NotFoundException("Bid not found");

        BidList bid = bidListRepository.getOne(id);
        return bid;

    }

    /**
     * Delete bid by id.
     *
     * @param id the id
     * @throws NotFoundException the not found exception
     */
    public void deleteBidById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete a bid by Id");

        if(!bidListRepository.existsById(id)) throw new NotFoundException("Bid not found");

        bidListRepository.deleteById(id);
    }

}
