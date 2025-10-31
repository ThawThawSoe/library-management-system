package com.example.library.book.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.book.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private UserRepository userRepository;
	
	 @Autowired
	 private AuthService authService;

	
	@PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
		
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        
        return token;
	}

}
