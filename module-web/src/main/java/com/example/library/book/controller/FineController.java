package com.example.library.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.library.book.entity.Fine;
import com.example.library.book.service.FineService;

@RestController
@RequestMapping("/api/fines")
public class FineController {
    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public List<Fine> getAllFines() {
        return fineService.getAllFines();
    }

    @PostMapping("/pay/{fineId}")
    public Fine payFine(@PathVariable Long fineId) {
        return fineService.payFine(fineId);
    }
}
