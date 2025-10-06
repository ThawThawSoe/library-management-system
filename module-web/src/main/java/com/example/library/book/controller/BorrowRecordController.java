package com.example.library.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.library.book.entity.BorrowRecord;
import com.example.library.book.service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrows")
public class BorrowRecordController {
	private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordService.getAllBorrowRecords();
    }

    @GetMapping("/{id}")
    public Optional<BorrowRecord> getBorrowRecordById(@PathVariable Long id) {
        return borrowRecordService.getBorrowRecordById(id);
    }

    @PostMapping("/borrow")
    public BorrowRecord borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return borrowRecordService.borrowBook(userId, bookId);
    }

    @PostMapping("/return/{borrowId}")
    public BorrowRecord returnBook(@PathVariable Long borrowId) {
        return borrowRecordService.returnBook(borrowId);
    }
}
