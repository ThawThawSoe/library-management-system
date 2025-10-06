package com.example.library.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.library.book.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class BooksController {
	
	@GetMapping("/books")
	public String books(HttpSession session,Model model) {
		 //model.addAttribute("content", "dashboard :: content"); // template :: fragment
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("currentUser", user);
		model.addAttribute("page", "books");
        return "layout"; // layout.html is rendered with fragment inside
	}
}
