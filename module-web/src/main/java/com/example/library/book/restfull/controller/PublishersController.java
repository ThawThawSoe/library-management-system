package com.example.library.book.restfull.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.book.repository.PublisherRepository;
import com.example.library.dto.PublisherDTO;

@RestController
@RequestMapping("/api/publishers")
public class PublishersController {
	@Autowired
	private PublisherRepository publisherRepository;
	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PublisherDTO> searchPublisher(@RequestParam(value="keyword", required= false) String keyword) {
		
		if(keyword.length() < 2) {
			return Collections.emptyList();
		}
		
		try {
		return publisherRepository.findTop5ByNameContainingIgnoreCase(keyword)
				.stream()
				.map(a -> new PublisherDTO(a.getPublisher_id(),a.getName()))
				.collect(Collectors.toList());
		}catch(Exception e) {
			return Collections.emptyList();
		}
		
	}

}
