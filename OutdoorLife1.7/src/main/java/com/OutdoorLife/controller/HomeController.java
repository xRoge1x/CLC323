package com.OutdoorLife.controller;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.OutdoorLife.LogComponent.LoggerInterceptor;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private LoggerInterceptor logger;
	
    @GetMapping("/")
    public String displayHome(Model model) {
    	
    	logger.log(Level.INFO, HomeController.class.getName(), "displayHome", "Home page displayed");
    	
        // Display Home Page
    	model.addAttribute("title", "Home Page");
        return "home";
    }
}
