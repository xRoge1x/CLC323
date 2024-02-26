package com.OutdoorLife.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.OutdoorLife.model.RegistrationModel;
import com.OutdoorLife.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String display(Model model) {
        // Display Registration Form View
        model.addAttribute("title", "Registration Form");
        model.addAttribute("registrationModel", new RegistrationModel());
        return "registration";
    }

    @PostMapping("/doRegistration")
    public String doRegistration(@Valid RegistrationModel registrationModel, BindingResult bindingResult, Model model) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Registration Form");
            return "registration";
        }

        userService.registerUser(
                registrationModel.getUsername(),
                registrationModel.getPassword(),
                registrationModel.getEmail()
        );

        return "redirect:/";
    }
}
