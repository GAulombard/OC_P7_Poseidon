package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
	public String home(Model model)
	{
		LOGGER.info("HTTP request received at / ");

		return "home";
	}

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin/home")
	public String adminHome(Model model){
		LOGGER.info("HTTP request received at /admin/home");

		return "redirect:/bidList/list";
	}


}
