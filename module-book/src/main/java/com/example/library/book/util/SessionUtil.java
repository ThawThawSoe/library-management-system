package com.example.library.book.util;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.example.library.book.entity.User;

@Component
public class SessionUtil {
	
	//Check user is logged in
	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("currentUser") != null;
	}
	
	
	//Get Current User
	public User getCurrentUser(HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
	
	//Redirect if not logged in
	public String redirectIfNotLoggedIn(HttpSession session) {
		if(!isLoggedIn(session)) {
			return "redirect:/login";
		}
		return null;
	}
}
