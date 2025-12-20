package com.example.library.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.book.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {	
	
	List<Author> findTop5ByNameContainingIgnoreCase(String name);
	
	
}
