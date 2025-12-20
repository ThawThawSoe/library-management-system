package com.example.library.book.repository;

import com.example.library.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= :startOfDay " +
			"AND u.createdAt < :endOfDay")
    long countByCreatedAtToday(@Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

	//List<User> findByFullNameContainingIgnoreCase(String keyword);
	List<User> findByFullNameContainingIgnoreCase(String keyword);
	
		
	
}
