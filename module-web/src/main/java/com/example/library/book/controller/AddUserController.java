package com.example.library.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.book.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddUserController {
	@Autowired
    private UserRepository userRepository;	
	
	
	@GetMapping("/addUser")
	public String addUser(HttpSession session,
			Model model)
			{
		
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		
		
   	 
		model.addAttribute("user", null);
		model.addAttribute("currentUser", user);
		model.addAttribute("roles", Arrays.asList("ADMIN", "LIBRARIAN", "STUDENT"));
		model.addAttribute("page", "addUser");
        return "layout"; // layout.html is rendered with fragment inside
	}
	
	
	}

