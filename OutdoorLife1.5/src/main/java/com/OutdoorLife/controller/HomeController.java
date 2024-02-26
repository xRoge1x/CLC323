package com.OutdoorLife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/")
    public String displayHome(Model model) {
        // Display Home Page
    	model.addAttribute("title", "Home Page");
        return "home";
    }
}
