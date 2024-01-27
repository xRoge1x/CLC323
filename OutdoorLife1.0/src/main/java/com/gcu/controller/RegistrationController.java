package com.gcu.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.model.Role;
import com.gcu.model.MyUser;
import com.gcu.repository.RoleRepository;
import com.gcu.repository.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "register"; // Assuming you have a register.html template in your resources/templates directory
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") MyUser user) {
        // Check if the username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            // I may want to handle this case differently (e.g., show an error message)
            return "redirect:/register?error";
        }

        // Encode the password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role "USER" to the user
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(Collections.singleton(userRole));
        
        // Save the user to the database
        userRepository.save(user);

        return "redirect:/login"; // Redirect to the login page after successful registration
    }
}
