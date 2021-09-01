package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class RatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /rating/list");

        List<Rating> ratingList = ratingService.findAll();

        model.addAttribute("ratingList", ratingList);

        return "rating/list";
    }


    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {

        LOGGER.info("HTTP GET request received at /rating/add");

        model.addAttribute("rating", new Rating());


        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result) {
        LOGGER.info("HTTP POST request received at /rating/validate");

        if (result.hasErrors()) {
            return "rating/add";
        }

        ratingService.save(rating);

        return "redirect:/rating/list";
    }


    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /rating/update/{id}");

        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);

        return "rating/update";
    }


    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        LOGGER.info("HTTP POST request received at /rating/update/{id}");

        if (result.hasErrors()) {
            return "rating/update";
        }

        ratingService.save(rating);

        return "redirect:/rating/list";
    }


    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /rating/delete/{id}");

        ratingService.deleteById(id);

        return "redirect:/rating/list";
    }
}