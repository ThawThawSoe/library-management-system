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
@Table(name = "categories")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
	
	@Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Book> books;
	

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
    
    
}
