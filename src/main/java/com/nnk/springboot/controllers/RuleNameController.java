package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * The type Rule name controller.
 */
@Controller
public class RuleNameController {
    // TODO: Inject RuleName service

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        return "ruleName/list";
    }

    /**
     * Add rule form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Validate string.
     *
     * @param ruleName the rule name
     * @param result   the result
     * @param model    the model
     * @return the string
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        return "ruleName/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        return "ruleName/update";
    }

    /**
     * Update rule name string.
     *
     * @param id       the id
     * @param ruleName the rule name
     * @param result   the result
     * @param model    the model
     * @return the string
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    /**
     * Delete rule name string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
