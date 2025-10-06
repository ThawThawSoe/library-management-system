package com.example.library.book.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "books")
public class Book {
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 * 
	 * private String title; private String author;
	 * 
	 * public Book() {}
	 * 
	 * public Book(String title, String author) { this.title = title; this.author =
	 * author; }
	 * 
	 * // Getters and Setters public Long getId() { return id; } public void
	 * setId(Long id) { this.id = id; } public String getTitle() { return title; }
	 * public void setTitle(String title) { this.title = title; } public String
	 * getAuthor() { return author; } public void setAuthor(String author) {
	 * this.author = author; }
	 */
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long bookId;

    @Column(nullable = false)
    private String title;

    private String author;
    private String publisher;
    private Integer publishedYear;

    @Column(unique = true)
    private String isbn;

    private String category;
    private Integer copiesTotal = 1;
    private Integer copiesAvailable = 1;
	public Long getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCopiesTotal() {
		return copiesTotal;
	}
	public void setCopiesTotal(Integer copiesTotal) {
		this.copiesTotal = copiesTotal;
	}
	public Integer getCopiesAvailable() {
		return copiesAvailable;
	}
	public void setCopiesAvailable(Integer copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
    
}
