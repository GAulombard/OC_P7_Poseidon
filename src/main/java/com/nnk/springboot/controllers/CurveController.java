package com.nnk.springboot.controllers;

import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.CurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nnk.springboot.domain.CurvePoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CurveController {

    private static Logger LOGGER = LoggerFactory.getLogger(CurveController.class);

    @Autowired
    private CurvePointService curvePointService;


    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /curvePoint/list");

        List<CurvePoint> curvePointList = curvePointService.findAll();

        model.addAttribute("curvePointList", curvePointList);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        LOGGER.info("HTTP GET request received at /curvePoint/add");

        model.addAttribute("curvePoint", new CurvePoint());

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result) throws AlreadyExistsException {
        LOGGER.info("HTTP POST request received at /curvePoint/validate");

        if (result.hasErrors()) {
            LOGGER.debug("Invalid fields");
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);

        return "redirect:/curvePoint/list";
    }


    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /curvePoint/update/{id}");

        CurvePoint curvePoint = curvePointService.findById(id);


        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }


    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) throws AlreadyExistsException {
        LOGGER.info("HTTP POST request received at /curvePoint/update/{id}");

        if (result.hasErrors()) {
            LOGGER.debug("CurveController (update) -> Invalid fields provided");
            return "curvePoint/update";
        }
        curvePointService.save(curvePoint);

        return "redirect:/curvePoint/list";
    }


    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /curvePoint/delete/{id}");
        curvePointService.deleteById(id);
        return "redirect:/curvePoint/list";
    }
}