package com.example.library.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.library.book.entity.Book;
import com.example.library.book.entity.User;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BooksController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public String books(HttpSession session,Model model,
			@RequestParam(defaultValue = "",required = false) String keyword) {
		 //model.addAttribute("content", "dashboard :: content"); // template :: fragment
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		
		List<Book> booklist = null;
		
		if(keyword == null || keyword.trim().isEmpty()) {
			
			booklist = bookService.getBooksByCategory((long)5);
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentUser", user);
		model.addAttribute("page", "books");
		model.addAttribute("booklist", booklist);
        return "layout"; // layout.html is rendered with fragment inside
	}
}
