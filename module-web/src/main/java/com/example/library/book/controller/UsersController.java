package com.example.library.book.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import jakarta.validation.Valid;

@Controller
public class UsersController {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/users")
	public String users(HttpSession session,
			Model model,@RequestParam(defaultValue = "",required = false) String keyword)
			{
		
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		List<User> userlist = null;
		 
		if(keyword == null || keyword.trim().isEmpty()) {
			userlist = userRepository.findAll();
		}else {
			//userlist = userRepository.findByFullNameContainingIgnoreCase(keyword);
			userlist = userRepository.findByFullNameContainingIgnoreCase(keyword, null);
		}
   	 
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentUser", user);
		model.addAttribute("page", "users");
		model.addAttribute("userlist", userlist);
        return "layout"; // layout.html is rendered with fragment inside
	}
	
	
	@GetMapping("/updateUser/{id}")
    public String deleteUser(@PathVariable Long id,HttpSession session,Model model) {
		
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser == null) {
			return "redirect:/login";
		}
		
		Optional<User> user =  userRepository.findById(id);
		//List<String> roles = Arrays.asList("ADMIN", "LIBRARIAN", "STUDENT");
		model.addAttribute("user", user);
		model.addAttribute("isEdit", true);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("roles", Arrays.asList("ADMIN", "LIBRARIAN", "STUDENT"));
		model.addAttribute("page", "addUser");
        return "layout"; // layout.html is rendered with fragment inside
		
        
    }
	
	@GetMapping("/addUser")
    public String showAddForm(HttpSession session,Model model) {        
        
        User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser == null) {
			return "redirect:/login";
		}		
		
		
		model.addAttribute("user", new User());
		model.addAttribute("isEdit", false);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("roles", Arrays.asList("ADMIN", "LIBRARIAN", "STUDENT"));
		model.addAttribute("page", "addUser");
        return "layout"; // layout.html is rendered with fragment inside
    }

	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,Model model, RedirectAttributes redirectAttributes) {
		 try {
			 userRepository.deleteById(id);
			 redirectAttributes.addFlashAttribute("success", "User deleted successfully!");
		 }  catch (Exception e) {
	            redirectAttributes.addFlashAttribute("error", "Failed to delete user!");
	        }
        return "redirect:/users";
    }
	
	@PostMapping("/save")
    public String saveUser(@ModelAttribute User user,BindingResult result, RedirectAttributes redirectAttributes) {
		
		
                  

		userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "User saved successfully!");
        return "redirect:/users";
    }
}
