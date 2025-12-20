package com.example.library.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.book.entity.Author;
import com.example.library.book.entity.Book;
import com.example.library.book.entity.Category;
import com.example.library.book.entity.Publisher;
import com.example.library.book.entity.User;
import com.example.library.book.repository.AuthorRepository;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.repository.CategoryRepository;
import com.example.library.book.repository.PublisherRepository;
import com.example.library.book.service.BookService;
import com.example.library.core.util.AppDataUtil;

import javax.servlet.http.HttpSession;

@Controller
public class BooksController {
	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	
	
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
			
			booklist = bookRepository.findAll();
		}else {
			 String titleParam = (title != null && !title.isEmpty()) ? title.trim() : null;
	          String authorParam = (author != null && !author.isEmpty()) ? author.trim() : null;
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
	
	@GetMapping("/addBook")
    public String showAddForm(HttpSession session,Model model) {        
        
        User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser == null) {
			return "redirect:/login";
		}		
		
		// If user object not already in model (from flash), create a new one
        if (!model.containsAttribute("user")) {
        	model.addAttribute("book", new Book());
        }
		
        List<Category> categorylist = categoryRepository.findAll();
		
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("isEdit", false);
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("page", "addBook");
        return "layout"; // layout.html is rendered with fragment inside
    }
	
	@PostMapping("/saveBook")
    public String saveUser(
    		@ModelAttribute Book book,
    		@RequestParam("authorId") Long authorId,
    		@RequestParam("categoryId") Long categoryId,
    		@RequestParam(value="publisherId",required=false) Long publisherId,
    		@RequestParam(value = "isEdit", required = false) Boolean isEdit,    		
    		 RedirectAttributes redirectAttributes
    		 ) {
		Publisher publisher = null;
		
		Author author = authorRepository.findById(authorId).orElseThrow(()->new RuntimeException("Author Not Found"));
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
		if(publisherId != null) {
			publisher = publisherRepository.findById(publisherId).orElseThrow(()->new RuntimeException("Publisher Not Found"));
		}
		
		
		
		book.setPublisher(publisher);
		book.setCategory(category);
		book.setAuthor(author);
		bookRepository.save(book);
		redirectAttributes.addFlashAttribute("success", "Book Saved successfully!");
        return "redirect:/books";
    }
}
