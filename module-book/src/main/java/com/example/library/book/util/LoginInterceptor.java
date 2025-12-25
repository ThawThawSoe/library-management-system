package com.example.library.book.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.library.book.entity.User;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect(request.getContextPath()+"/login");
            return false; // Stop further handling
        }

        // User is logged in, continue to controller
        return true;
    }
}
