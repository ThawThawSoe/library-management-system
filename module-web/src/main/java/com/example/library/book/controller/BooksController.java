package com.example.library.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.library.book.entity.Book;
import com.example.library.book.entity.Category;
import com.example.library.book.entity.User;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.repository.CategoryRepository;
import com.example.library.book.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BooksController {
	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public String books(HttpSession session,Model model,
			@RequestParam(required = false) String title,
	        @RequestParam(required = false) String author,
	        @RequestParam(required = false) Long categoryId) {
		 //model.addAttribute("content", "dashboard :: content"); // template :: fragment
		User user = (User) session.getAttribute("currentUser");
		if(user == null) {
			return "redirect:/login";
		}
		
		List<Book> booklist = null;
		
		if(categoryId == null || author == null || title == null) {
			
			booklist = bookService.getBooksByCategory((long)5);
		}else {
			 String titleParam = (title != null && !title.isBlank()) ? title.trim() : null;
	          String authorParam = (author != null && !author.isBlank()) ? author.trim() : null;
	          booklist = bookRepository.search(titleParam, authorParam, categoryId);
		}
		List<Category> categorylist = categoryRepository.findAll();
		
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("title", title);
		model.addAttribute("author", author);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("currentUser", user);
		model.addAttribute("page", "books");
		model.addAttribute("booklist", booklist);
        return "layout"; // layout.html is rendered with fragment inside
	}
}
