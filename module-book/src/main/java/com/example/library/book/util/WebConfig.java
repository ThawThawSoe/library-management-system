package com.example.library.book.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
    private LoginInterceptor loginInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/dashboard/**",
                				"/books",
                				"/addBook/**",
                				"/saveBook",
                				"/users/**",
                				"/updateUser/**",
                				"/addUser",
                				"/delete/**",
                				"/save",
                				"/borrowRecord/**") // URLs that need login
                .excludePathPatterns("/login", "/images", "/css/**", "/js/**"); // exclude public URLs
    }
	
}
