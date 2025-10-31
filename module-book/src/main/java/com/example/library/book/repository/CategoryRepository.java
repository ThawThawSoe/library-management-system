package com.example.library.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.book.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	

}
