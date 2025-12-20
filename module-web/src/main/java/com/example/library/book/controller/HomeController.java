package com.example.library.book.controller;

import java.util.Optional;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.book.service.UserService;


import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping("/login")   // http://localhost:8080/login
    public String login() {
        return "login";    // looks for templates/login.html
    }
	@PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
		
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		
       
		 if (optionalUser.isPresent()) {
			 User user = optionalUser.get();  
			 if (user.getPassword().equals(password)) {
	                // Store user in session
	                session.setAttribute("currentUser", user);

	                // Redirect to dashboard
	                return "redirect:/dashboard";
	            } else {
	                model.addAttribute("error", "Invalid password");
	                return "login";
	            }
		 }else {
			 model.addAttribute("error", "User not found");
	          return "login";
        }
    }
	

}
