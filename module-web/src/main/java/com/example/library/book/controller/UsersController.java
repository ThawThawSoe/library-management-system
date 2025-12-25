package com.example.library.book.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.book.entity.Book;
import com.example.library.book.entity.Role;
import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.book.service.UserService;
import com.example.library.core.util.AppDataUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


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
		
		List<User> userlist = null;
		 
		if(keyword == null || keyword.trim().isEmpty()) {
			userlist = userRepository.findAll();
		}else {
			//userlist = userRepository.findByFullNameContainingIgnoreCase(keyword);
			userlist = userRepository.findByFullNameContainingIgnoreCase(keyword);
			//userRepository.findby
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
		
		
		User user =  userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		//List<String> roles = Arrays.asList("ADMIN", "LIBRARIAN", "STUDENT");
		model.addAttribute("user", user);
		model.addAttribute("isEdit", true);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("roles", Role.values());
		model.addAttribute("page", "addUser");
        return "layout"; // layout.html is rendered with fragment inside
		
        
    }
	
	@GetMapping("/addUser")
    public String showAddForm(HttpSession session,Model model) {        
        
        User currentUser = (User) session.getAttribute("currentUser");
			
		
		// If user object not already in model (from flash), create a new one
        if (!model.containsAttribute("user")) {
        	model.addAttribute("user", new User());
        }
		
		model.addAttribute("isEdit", false);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("roles", Role.values());
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
    public String saveUser(
    		@Valid @ModelAttribute("user") User user,
    		BindingResult result,
    		@RequestParam(value = "isEdit", required = false) Boolean isEdit,  
    		HttpSession session,Model model,
    		 RedirectAttributes redirectAttributes
    		 ) {
		
		User currentUser = (User) session.getAttribute("currentUser");        
		model.addAttribute("currentUser", currentUser);
		
		if (result.hasErrors()) {
			model.addAttribute("isEdit", isEdit);
			model.addAttribute("roles", Role.values());
	        model.addAttribute("page", "addUser");
	        return "layout";
	    }
		
		
		    
		if(isEdit) {
			
			userService.updateUser(user,currentUser);
	        redirectAttributes.addFlashAttribute("success", "User Updated successfully!");
		}else {
			//check user name already exist
			if(!userRepository.findByUsername(user.getUsername()).isPresent()) {
				
				userService.saveUser(user,currentUser);
		        redirectAttributes.addFlashAttribute("success", "User saved successfully!");
			}else {	
				redirectAttributes.addFlashAttribute("user", user);
				redirectAttributes.addFlashAttribute("error", "UserName Already Exit!");
				return "redirect:/addUser";
			}
			
		}
		
        return "redirect:/users";
    }
}
