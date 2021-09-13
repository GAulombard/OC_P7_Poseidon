package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.BidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Bid list controller.
 */
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class BidListController {

    private static Logger LOGGER = LoggerFactory.getLogger(BidListController.class);

    @Autowired
    private BidListService bidListService;

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        LOGGER.info("HTTP GET request received at /bidList/list");

        model.addAttribute("bidList",bidListService.findAll());

        return "bidList/list";
    }

    /**
     * Add form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/bidList/add")
    public String addForm(Model model) {
        LOGGER.info("HTTP GET request received at /bidList/add");

        model.addAttribute("bidList", new BidList());

        return "bidList/add";
    }

    /**
     * Validate string.
     *
     * @param bid           the bid
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bid, BindingResult bindingResult) {
        LOGGER.info("HTTP POST request received at /bidList/validate");

        if(bindingResult.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "bidList/add";
        }

        bidListService.save(bid);

        return "redirect:/bidList/list";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /bidList/update/{id}");

        model.addAttribute("bidList",bidListService.findBidById(id));

        return "bidList/update";
    }

    /**
     * Update string.
     *
     * @param id            the id
     * @param bidList       the bid list
     * @param bindingResult the binding result
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @PostMapping("/bidList/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult bindingResult) throws NotFoundException {
        LOGGER.info("HTTP POST request received at /bidList/update/{id}");

       if(bindingResult.hasErrors()){
           LOGGER.info("Invalid field error");
           return "bidList/list";
       }

       BidList bidToUpdate = bidListService.findBidById(id);

       bidToUpdate.setAccount(bidList.getAccount());
       bidToUpdate.setType(bidList.getType());
       bidToUpdate.setBidQuantity(bidList.getBidQuantity());
       bidToUpdate.setAskQuantity(bidList.getAskQuantity());
       bidToUpdate.setBid(bidList.getBid());
       bidToUpdate.setAsk(bidList.getAsk());
       bidListService.save(bidToUpdate);


        return "redirect:/bidList/list";
    }

    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/bidList/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /bidList/delete/{id}");

        bidListService.deleteBidById(id);

        return "redirect:/bidList/list";
    }
}
