package com.example.library.book.controller;

import com.example.library.book.entity.Book;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.service.BookService;

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

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
