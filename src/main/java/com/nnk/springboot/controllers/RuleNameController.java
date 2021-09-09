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

@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class RuleNameController {

    private static Logger LOGGER = LoggerFactory.getLogger(RuleNameController.class);

    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        LOGGER.info("HTTP request received at /ruleName/list");

        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("ruleNameList", ruleNameList);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addForm(Model model) {
        LOGGER.info("HTTP GET request received at /ruleName/add");

        model.addAttribute("ruleName", new RuleName());

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result) {
        LOGGER.info("HTTP POST request received at /ruleName/validate");

        if (result.hasErrors()) {
            return "ruleName/add";
        }

        ruleNameService.save(ruleName);

        return "redirect:/ruleName/list";
    }


    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /ruleName/update/{id}");

        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }


    @PostMapping("/ruleName/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        LOGGER.info("HTTP POST request received at /ruleName/update/{id}");

        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleNameService.save(ruleName);

        return "redirect:/ruleName/list";
    }


    @GetMapping("/ruleName/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /ruleName/delete/{id}");

        ruleNameService.deleteById(id);

        return "redirect:/ruleName/list";
    }
}
