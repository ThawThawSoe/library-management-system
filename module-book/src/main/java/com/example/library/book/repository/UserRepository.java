package com.example.library.book.repository;

import com.example.library.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	@Query("SELECT COUNT(u) FROM User u WHERE DATE(u.createdAt) = :today")
    long countByCreatedAtToday(@Param("today") LocalDate today);

	//List<User> findByFullNameContainingIgnoreCase(String keyword);
	//List<User> findByFullNameContainingIgnoreCase(String keyword);
	
		
	
}
