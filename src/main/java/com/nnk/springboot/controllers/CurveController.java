package com.nnk.springboot.controllers;

import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.CurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * The type Curve controller.
 */
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class CurveController {

    private static Logger LOGGER = LoggerFactory.getLogger(CurveController.class);

    @Autowired
    private CurvePointService curvePointService;


    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /curvePoint/list");

        List<CurvePoint> curvePointList = curvePointService.findAll();

        model.addAttribute("curvePointList", curvePointList);

        return "curvePoint/list";
    }

    /**
     * Add form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/curvePoint/add")
    public String addForm(Model model) {
        LOGGER.info("HTTP GET request received at /curvePoint/add");

        model.addAttribute("curvePoint", new CurvePoint());

        return "curvePoint/add";
    }

    /**
     * Validate string.
     *
     * @param curvePoint the curve point
     * @param result     the result
     * @return the string
     * @throws AlreadyExistsException the already exists exception
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result) throws AlreadyExistsException {
        LOGGER.info("HTTP POST request received at /curvePoint/validate");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);

        return "redirect:/curvePoint/list";
    }


    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /curvePoint/update/{"+id+"}");

        CurvePoint curvePoint = curvePointService.findById(id);


        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }


    /**
     * Update string.
     *
     * @param id         the id
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the string
     * @throws AlreadyExistsException the already exists exception
     */
    @PostMapping("/curvePoint/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) throws AlreadyExistsException {
        LOGGER.info("HTTP POST request received at /curvePoint/update/{"+id+"}");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "curvePoint/update";
        }
        curvePointService.save(curvePoint);

        return "redirect:/curvePoint/list";
    }


    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /curvePoint/delete/{"+id+"}");
        curvePointService.deleteById(id);
        return "redirect:/curvePoint/list";
    }
}