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

import com.example.library.book.repository.CategoryRepository;
import com.example.library.dto.CategoryDTO;

@RestController
@RequestMapping("/api/categorys")
public class CategorysController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> searchCategory(@RequestParam(value="keyword",required = false) String keyword) {
		System.out.println("you coming?");
		if(keyword.length() < 2) {
			return Collections.emptyList();
		}
		try {
		return categoryRepository.findTop5ByNameContainingIgnoreCase(keyword)
				.stream()
				.map(a -> new CategoryDTO(a.getCategory_id(),a.getName()))
				.collect(Collectors.toList());
		}catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
	

}
