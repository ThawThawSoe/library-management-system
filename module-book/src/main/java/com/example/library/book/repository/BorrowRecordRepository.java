package com.example.library.book.repository;


import com.example.library.book.entity.BorrowRecord;
import com.example.library.book.entity.User;
import com.example.library.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long>{
	   List<BorrowRecord> findByUser(User user);
	   List<BorrowRecord> findByBook(Book book);
}
