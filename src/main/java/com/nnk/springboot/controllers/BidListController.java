package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * The type Bid list controller.
 */
@Controller
public class BidListController {
    // TODO: Inject Bid service

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        return "bidList/list";
    }

    /**
     * Add bid form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Validate string.
     *
     * @param bid    the bid
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        return "bidList/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        return "bidList/update";
    }

    /**
     * Update bid string.
     *
     * @param id      the id
     * @param bidList the bid list
     * @param result  the result
     * @param model   the model
     * @return the string
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        return "redirect:/bidList/list";
    }

    /**
     * Delete bid string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        return "redirect:/bidList/list";
    }
}
