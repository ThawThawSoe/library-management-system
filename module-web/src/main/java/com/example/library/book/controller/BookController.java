package com.example.library.book.controller;

import com.example.library.book.entity.Book;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.service.BookService;
import com.example.library.core.util.JwtUtil;

import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.List;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
	

	/*
	 * // Add sample data on startup
	 * 
	 * @PostConstruct public void init() { if (bookRepository.count() == 0) {
	 * bookRepository.save(new Book("Harry Potter", "J.K. Rowling"));
	 * bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien")); } }
	 */

	private final BookService bookService;
	private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
		this.bookRepository = bookRepository;
    }
    
    
    
//
//    @GetMapping("/getAllBooks")
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @PostMapping("/createBook")
//    public Book createBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
    
    
    @GetMapping
    public List<Book> getAllBooks(@RequestHeader("Authorization") String token) {
        // ✅ Remove "Bearer " from token
        token = token.replace("Bearer ", "");

        // ✅ Verify token before returning data
        if (!JwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        return bookRepository.findByCategoryId((long)5);
    }
}
