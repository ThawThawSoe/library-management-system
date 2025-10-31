package com.example.library.book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.core.util.JwtUtil;

@Service
public class AuthService {
	@Autowired
    private UserRepository userRepository;
	
	 public String authenticate(String username, String password) {
	        // check username & password (from DB)
		 
		 Optional<User> optionalUser = userRepository.findByUsername(username);
		 
		 if(optionalUser.isPresent()) {
			 User user = optionalUser.get();
			 if(user.getPassword().equals(password)) {
				 return JwtUtil.generateToken(username);
			 }else {
				 throw new RuntimeException("Invalid credentials");
			 }
		 }
	     return "UserName is not exist";   
	 }

}
