package com.example.library.book.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library.book.entity.Book;
import com.example.library.book.entity.BorrowRecord;
import com.example.library.book.entity.Fine;
import com.example.library.book.entity.User;
import com.example.library.book.repository.BookRepository;
import com.example.library.book.repository.BorrowRecordRepository;
import com.example.library.book.repository.FineRepository;
import com.example.library.book.repository.UserRepository;


@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final FineRepository fineRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,
                               UserRepository userRepository,
                               BookRepository bookRepository,
                               FineRepository fineRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.fineRepository = fineRepository;
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public Optional<BorrowRecord> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }

    public BorrowRecord borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getCopiesAvailable() <= 0) {
            throw new RuntimeException("No copies available for this book");
        }

        // reduce available copies
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);

        // create borrow record
        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14)); // 2 weeks loan
        record.setStatus(BorrowRecord.Status.BORROWED);

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long borrowId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.getStatus() == BorrowRecord.Status.RETURNED) {
            throw new RuntimeException("Book already returned");
        }

        // mark as returned
        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowRecord.Status.RETURNED);

        // increase available copies
        Book book = record.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);
        
        if (record.getReturnDate().isAfter(record.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
                    record.getDueDate(), record.getReturnDate());

            double fineAmount = daysLate * 100.0; // e.g., 100 Kyats per day
            Fine fine = new Fine();
            fine.setBorrowRecord(record);
            fine.setAmount(fineAmount);
            fine.setPaid(false);

            // Save fine (need repository injection)
            fineRepository.save(fine);
        }      
        

        return borrowRecordRepository.save(record);
    }
    
}
