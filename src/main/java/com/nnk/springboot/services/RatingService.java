package com.nnk.springboot.services;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Rating service.
 */
@Service
public class RatingService {

    private static Logger LOGGER = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Rating> findAll() {
        LOGGER.info("Process to find all rating");

        return ratingRepository.findAll();
    }

    /**
     * Save.
     *
     * @param rating the rating
     */
    public void save(Rating rating) {
        LOGGER.info("Process to save new rating");

        ratingRepository.save(rating);
    }

    /**
     * Find by id rating.
     *
     * @param id the id
     * @return the rating
     * @throws NotFoundException the not found exception
     */
    public Rating findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find rating by id");

        if(!ratingRepository.existsById(id)) throw new NotFoundException("rating not found");

        return ratingRepository.getOne(id);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws NotFoundException the not found exception
     */
    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete rating by id");

        if(!ratingRepository.existsById(id)) throw new NotFoundException("rating not found");

        ratingRepository.deleteById(id);

    }
}
