package com.example.library.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.book.entity.Fine;
import com.example.library.book.repository.FineRepository;

@Service
public class FineService {
    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    public Fine payFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaid(true);
        return fineRepository.save(fine);
    }
    
}
