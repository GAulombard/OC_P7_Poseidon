package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * The type Curve controller.
 */
@Controller
public class CurveController {
    // TODO: Inject Curve Point service

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        return "curvePoint/list";
    }

    /**
     * Add bid form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Validate string.
     *
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the string
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        return "curvePoint/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        return "curvePoint/update";
    }

    /**
     * Update bid string.
     *
     * @param id         the id
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the string
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }

    /**
     * Delete bid string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        return "redirect:/curvePoint/list";
    }
}
