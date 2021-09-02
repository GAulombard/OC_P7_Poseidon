package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    private static Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /trade/list");

        List<Trade> tradeList = tradeService.findAll();

        model.addAttribute("tradeList", tradeList);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        LOGGER.info("HTTP GET request received at /trade/add");

        model.addAttribute("trade", new Trade());

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result) {
        LOGGER.info("HTTP POST request received at /trade/validate");

        if (result.hasErrors()) {
            return "trade/add";
        }

        tradeService.save(trade);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /trade/update/{id}");

        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result) {
        LOGGER.info("HTTP POST request received at /trade/update/{id}");

        if (result.hasErrors()) {
            return "trade/update";
        }

        tradeService.save(trade);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP request received at /trade/delete/{id}");

        tradeService.deleteById(id);

        return "redirect:/trade/list";
    }
}
