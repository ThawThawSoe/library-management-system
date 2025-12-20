package com.example.library.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.book.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
	List<Publisher> findTop5ByNameContainingIgnoreCase(String name);

}
