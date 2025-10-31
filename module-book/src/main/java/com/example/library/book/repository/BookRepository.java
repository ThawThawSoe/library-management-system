package com.example.library.book.repository;

import com.example.library.book.entity.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	 List<Book> findByTitleContainingIgnoreCase(String title);
	//List<Book> findByAuthorContainingIgnoreCase(String author);
	 
	 @Query("SELECT b FROM Book b WHERE b.category.id = :categoryId")
	 List<Book> findByCategoryId(@Param("categoryId") Long categoryId);
	 
	 @Query("""
		        select b from Book b
		        where (:title is null or lower(b.title) like lower(concat('%', :title, '%')))
		          AND (:author IS NULL OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :author, '%')))
		          and (:categoryId is null or b.category.id = :categoryId)
		        """)
		    List<Book> search(
		        @Param("title") String title,
		        @Param("author") String author,
		        @Param("categoryId") Long categoryId
		    );
}
