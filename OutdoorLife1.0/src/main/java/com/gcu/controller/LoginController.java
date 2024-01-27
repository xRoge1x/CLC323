package com.gcu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.model.MyUser;
import com.gcu.repository.UserRepository;
import com.gcu.security.CustomUserDetails;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
    	
        // Retrieve user from the database based on the provided username
        MyUser user = userRepository.findByUsername(username);

        // Check if the user exists and the password is correct
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
        	
            // Create an authentication token and set it to the SecurityContext
            UserDetails userDetails = CustomUserDetails.build(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect to the home page on successful login
            return "redirect:/home";
            
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
}
