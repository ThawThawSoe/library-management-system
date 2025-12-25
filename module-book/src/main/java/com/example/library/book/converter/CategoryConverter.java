package com.example.library.book.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.library.book.entity.Category;
import com.example.library.book.repository.CategoryRepository;

@Component
public class CategoryConverter implements Converter<String,Category> {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category convert(String source) {
		// TODO Auto-generated method stub
		if(source == null || source.isEmpty() ) {
			return null;
		}
		return categoryRepository.findById(Long.valueOf(source)).orElse(null);
	}

}
