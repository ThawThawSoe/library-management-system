package com.example.library.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.library.book.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		User user = (User) session.getAttribute("currentUser");
		if(user != null) {
			session.invalidate();
			
		}
		
       return "redirect:/login";
	}

}
