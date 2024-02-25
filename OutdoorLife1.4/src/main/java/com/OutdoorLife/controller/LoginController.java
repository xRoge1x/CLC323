package com.OutdoorLife.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.OutdoorLife.model.LoginModel;
import com.OutdoorLife.model.RegistrationModel;
import com.OutdoorLife.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String display(Model model) {
        // Display Login Form View
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
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

    @GetMapping("/registration")
    public String displayRegistration(Model model) {
        // Display Registration Form View
        model.addAttribute("title", "Registration Form");
        model.addAttribute("registrationModel", new RegistrationModel());
        return "registration";
    }

}

