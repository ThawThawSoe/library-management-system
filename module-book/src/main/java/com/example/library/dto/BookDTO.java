package com.example.library.dto;

import com.example.library.book.entity.Book;

public class BookDTO {
	
	private Long id;
	private String title;
	private String authorName;
    private String categoryName;
    private String publisherName;
    
    public BookDTO(Book book) { 
    	this.id = book.getBookId();
        this.title = book.getTitle();
        this.authorName = book.getAuthor() != null ? book.getAuthor().getName() : null;
        this.categoryName = book.getCategory() != null ? book.getCategory().getName() : null;
        this.publisherName = book.getPublisher() != null ? book.getPublisher().getName() : null;
    }
    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthorName() { return authorName; }
    public String getCategoryName() { return categoryName; }
    public String getPublisherName() { return publisherName; }
}
