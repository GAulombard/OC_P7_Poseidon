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

@Service
public class RatingService {

    private static Logger LOGGER = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        LOGGER.info("Process to find all rating");

        return ratingRepository.findAll();
    }

    public void save(Rating rating) {
        LOGGER.info("Process to save new rating");

        ratingRepository.save(rating);
    }

    public Rating findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find rating by id");

        if(!ratingRepository.existsById(id)) throw new NotFoundException("rating not found");

        return ratingRepository.getOne(id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete rating by id");

        if(!ratingRepository.existsById(id)) throw new NotFoundException("rating not found");

        ratingRepository.deleteById(id);

    }
}
