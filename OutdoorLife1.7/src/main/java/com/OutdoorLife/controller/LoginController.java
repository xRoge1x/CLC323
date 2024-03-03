package com.OutdoorLife.controller;

import javax.validation.Valid;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.model.LoginModel;
import com.OutdoorLife.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private LoggerInterceptor logger;
    
    @GetMapping("/")
    public String display(Model model) {
        // Display Login Form View
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        logger.log(Level.INFO, LoginController.class.getName(), "display", "Login page displayed");
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        if (userService.authenticateUser(loginModel.getUsername(), loginModel.getPassword())) {
            // Authentication success logic
        	// Return home page
            model.addAttribute("title", "Home Page");
            logger.log(Level.INFO, LoginController.class.getName(), "doLogin", "Login submitted");
            return "home";
        } 
        else 
        {
            // Authentication failure logic
            model.addAttribute("title", "Login Form");
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
    // deleted registration GetMapping from here 03-03-24
}

