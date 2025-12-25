package com.example.library.book.controller;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.library.book.util.SessionUtil;
import com.example.library.core.util.AppDataUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class BooksController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@ModelAttribute
	public void commonModel(Model model, HttpSession session) {
	    User currentUser = (User) session.getAttribute("currentUser");
	    model.addAttribute("currentUser", currentUser);
	}
		
	@GetMapping("/books")
	public String books(HttpSession session,Model model,
			@RequestParam(required = false) String title,
	        @RequestParam(required = false) String author,
	        @RequestParam(required = false) Long categoryId) {
		 //model.addAttribute("content", "dashboard :: content"); // template :: fragment
		User user = (User) session.getAttribute("currentUser");	
		 
		
		List<Book> booklist = null;
		
		if(categoryId == null && author == null && title == null) {
			
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
        	
		
		// If user object not already in model (from flash), create a new one
        if (!model.containsAttribute("book")) {
        	Book book = new Book();
        	book.setAuthor(new Author());
        	book.setCategory(new Category());
        	book.setPublisher(new Publisher());
        	model.addAttribute("book", book);
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
    		@Valid @ModelAttribute("book") Book book,
    		BindingResult result,
    		@RequestParam(value = "isEdit", required = false) Boolean isEdit,    		   		 
    		 HttpSession session,Model model,
    		 RedirectAttributes redirectAttributes
    		 ) {
		
		User currentUser = (User) session.getAttribute("currentUser");        
		model.addAttribute("currentUser", currentUser);
		
		
		Publisher publisher = null;
		Author author = null;
		Category category = null;
		if(book.getAuthor() != null && book.getAuthor().getAuthor_id() !=null) {
			 author = authorRepository.findById(book.getAuthor().getAuthor_id()).orElseThrow(()->new RuntimeException("Author Not Found"));
		}
		if(book.getCategory() != null && book.getCategory().getCategory_id() != null) {
			category = categoryRepository.findById(book.getCategory().getCategory_id()).orElseThrow(()->new RuntimeException("Category Not Found"));
		}	
		
		if(book.getPublisher() != null && book.getPublisher().getPublisher_id() != null) {
			publisher = publisherRepository.findById(book.getPublisher().getPublisher_id()).orElseThrow(()->new RuntimeException("Publisher Not Found"));
		}
		
		
		book.setPublisher(publisher);
		book.setCategory(category);
		book.setAuthor(author);	
		
		
		if (result.hasErrors()) {
			model.addAttribute("isEdit", isEdit);
	        model.addAttribute("categorylist", categoryRepository.findAll());
	        model.addAttribute("page", "addBook");
	        return "layout";
	    }
		
		
		if(isEdit) {
			
			bookService.UpdateBook(book, currentUser);
			redirectAttributes.addFlashAttribute("success", "Book Saved successfully!");
			
		}else {
			boolean existISBN = bookRepository.existISBN(book.getIsbn());
			if(existISBN) {
				redirectAttributes.addFlashAttribute("book", book);
				redirectAttributes.addFlashAttribute("isbnError", "ISBN Already Exit!");
				return "redirect:/addBook";
			}		
			
			bookService.saveBook(book, currentUser);
			redirectAttributes.addFlashAttribute("success", "Book Saved successfully!");
		}
		
		
		
		
        return "redirect:/books";
    }
	@GetMapping("/updateBook/{id}")
	public String UpdateBook(
			@PathVariable Long id,HttpSession session,Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		Book book = bookRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Book Not Found"));
		
		 if (book.getAuthor() == null) {
		        book.setAuthor(new Author());
		  }
		 
		 if(book.getCategory() == null) {
			 book.setCategory(new Category());
		 }
		 
		 if(book.getPublisher() == null) {
			 book.setPublisher(new Publisher());
		 }
		 List<Category> categorylist = categoryRepository.findAll();
			
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("book", book);
		model.addAttribute("isEdit", true);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("page", "addBook");
		return "layout";
	}
}
