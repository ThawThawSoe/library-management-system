package com.example.library.book.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



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

   
    @Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci",
    nullable = false)
    @NotBlank(message = "Title is required")
    @Size(max = 100, message="Title must not exceed 100 characters")
    private String title;    
    
    @NotNull(message = "Publisher Year is required")
    @Min(value = 1900, message = "Publisher year must be >= 1900")
    @Max(value = 2100, message = "Publisher year is invalid")
    private Integer publishedYear;

    @Column(unique = true)
    @NotBlank(message = "ISBN is required")
    @Size(max = 200, message = "ISBN must not exceed 200 characters")
    private String isbn;

    @NotNull(message = "Total is required")
    @Min(value = 1, message = "Total must be >= 1")
    private Integer copiesTotal;
    
    @NotNull(message = "Copy Available is required")
    @Min(value = 0, message = "Copy Available must be >= 0")
    private Integer copiesAvailable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    @NotNull(message = "Author is required")
    private Author author;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    @NotNull(message = "Category is required")
    private Category category;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    @JsonManagedReference
    private Publisher publisher;
    
    
    @Column
    private LocalDateTime   createdAt = LocalDateTime.now();
    
    @Column(length = 50)
    private String createdBy;
    
    @Column
    private LocalDateTime updatedAt;

    @Column(length = 50)
    private String updatedBy;
    
	public Long getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
    
}
