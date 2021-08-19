package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * The type Rating controller.
 */
@Controller
public class RatingController {
    // TODO: Inject Rating service

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        return "rating/list";
    }

    /**
     * Add rating form string.
     *
     * @param rating the rating
     * @return the string
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Validate string.
     *
     * @param rating the rating
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        return "rating/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        return "rating/update";
    }

    /**
     * Update rating string.
     *
     * @param id     the id
     * @param rating the rating
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    /**
     * Delete rating string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}
