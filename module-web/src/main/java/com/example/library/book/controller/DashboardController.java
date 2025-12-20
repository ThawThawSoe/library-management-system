package com.example.library.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.library.book.entity.User;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session,Model model) {
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("currentUser", user);
		model.addAttribute("page", "dashboard");
        return "layout"; // layout.html is rendered with fragment inside
	}
}
