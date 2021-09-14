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

/**
 * The type Rating controller.
 */
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class RatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /rating/list");

        List<Rating> ratingList = ratingService.findAll();

        model.addAttribute("ratingList", ratingList);

        return "rating/list";
    }


    /**
     * Add form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/rating/add")
    public String addForm(Model model) {

        LOGGER.info("HTTP GET request received at /rating/add");

        model.addAttribute("rating", new Rating());


        return "rating/add";
    }

    /**
     * Validate string.
     *
     * @param rating the rating
     * @param result the result
     * @return the string
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result) {
        LOGGER.info("HTTP POST request received at /rating/validate");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "rating/add";
        }

        ratingService.save(rating);

        return "redirect:/rating/list";
    }


    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /rating/update/{"+id+"}");

        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);

        return "rating/update";
    }


    /**
     * Update string.
     *
     * @param id     the id
     * @param rating the rating
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/rating/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        LOGGER.info("HTTP POST request received at /rating/update/{"+id+"}");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "rating/update";
        }

        ratingService.save(rating);

        return "redirect:/rating/list";
    }


    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/rating/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /rating/delete/{"+id+"}");

        ratingService.deleteById(id);

        return "redirect:/rating/list";
    }
}