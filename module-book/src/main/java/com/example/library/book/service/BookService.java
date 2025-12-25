package com.example.library.book.service;


import com.example.library.book.entity.Book;
import com.example.library.book.entity.User;
import com.example.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
//	 private final BookRepository repository;

//	    public BookService(BookRepository repository) { this.repository = repository; }
//
//	    public List<Book> getAllBooks() { return repository.findAll(); }
//	    public Optional<Book> getBook(Long id) { return repository.findById(id); }
//	    public Book saveBook(Book book) { return repository.save(book); }
//	    public void deleteBook(Long id) { repository.deleteById(id); }
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book saveBook(Book book,User currentUser) {
    	book.setCreatedBy(currentUser.getUsername());
    	
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
    
    public Book UpdateBook(Book updateBook,User currentUser) {
    	Book existingBook = bookRepository.findById(updateBook.getBookId())
    			.orElseThrow(() -> new RuntimeException("Book Not found"));
    	existingBook.setTitle(updateBook.getTitle());
    	existingBook.setIsbn(updateBook.getIsbn());    	
    	existingBook.setCopiesTotal(updateBook.getCopiesTotal());
    	existingBook.setCopiesAvailable(updateBook.getCopiesAvailable());
    	existingBook.setPublishedYear(updateBook.getPublishedYear());
    	existingBook.setAuthor(updateBook.getAuthor());
    	existingBook.setPublisher(updateBook.getPublisher());
    	existingBook.setCategory(updateBook.getCategory());
    	
    	existingBook.setUpdatedAt(LocalDateTime.now());
    	existingBook.setUpdatedBy(currentUser.getUsername());
    	
    	return bookRepository.save(existingBook);
    }
	

}
