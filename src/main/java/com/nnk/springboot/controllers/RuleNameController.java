package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.services.RuleNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Rule name controller.
 */
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class RuleNameController {

    private static Logger LOGGER = LoggerFactory.getLogger(RuleNameController.class);

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /ruleName/list");

        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("ruleNameList", ruleNameList);

        return "ruleName/list";
    }

    /**
     * Add form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/ruleName/add")
    public String addForm(Model model) {
        LOGGER.info("HTTP GET request received at /ruleName/add");

        model.addAttribute("ruleName", new RuleName());

        return "ruleName/add";
    }

    /**
     * Validate string.
     *
     * @param ruleName the rule name
     * @param result   the result
     * @return the string
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result) {
        LOGGER.info("HTTP POST request received at /ruleName/validate");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "ruleName/add";
        }

        ruleNameService.save(ruleName);

        return "redirect:/ruleName/list";
    }


    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /ruleName/update/{"+id+"}");

        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }


    /**
     * Update string.
     *
     * @param id       the id
     * @param ruleName the rule name
     * @param result   the result
     * @return the string
     */
    @PostMapping("/ruleName/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result) {
        LOGGER.info("HTTP POST request received at /ruleName/update/{"+id+"}");

        if (result.hasErrors()) {
            LOGGER.info("Invalid field error");
            return "ruleName/update";
        }

        ruleNameService.save(ruleName);

        return "redirect:/ruleName/list";
    }


    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     * @throws NotFoundException the not found exception
     */
    @GetMapping("/ruleName/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /ruleName/delete/{"+id+"}");

        ruleNameService.deleteById(id);

        return "redirect:/ruleName/list";
    }
}
