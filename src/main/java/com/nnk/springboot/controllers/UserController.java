package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model, Principal user)
    {
        LOGGER.info("HTTP request received at /user/list");

        model.addAttribute("users", userService.findAll());

        return "user/list";
    }

    @GetMapping("/user/add")
    public String addForm(Model model) {
        LOGGER.info("HTTP Get request received at /user/add");

        model.addAttribute("user",new User());

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws AlreadyExistsException {
        LOGGER.info("HTTP POST request received at /user/validate");

        if (result.hasErrors()) {
            return "user/add";
        }

        userService.save(user);

        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /user/update/{id}");

        User user = userService.findById(id);

        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) throws NotFoundException {
        LOGGER.info("HTTP POST request received at /user/update/{id}");

        if (result.hasErrors()) {
            return "user/update";
        }

        userService.update(user);


        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) throws NotFoundException {
        LOGGER.info("HTTP GET request received at /user/delete/{id}");

        userService.deleteById(id);

        return "redirect:/user/list";
    }
}
