package com.example.library.book.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author_id;
	
	@Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci")
    private String name;
	
	
    private String bio;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Book> books;

	

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
    
    
}
