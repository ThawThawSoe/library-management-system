package com.example.library.book.service;
import org.springframework.stereotype.Service;

import com.example.library.book.entity.User;
import com.example.library.book.repository.UserRepository;
import com.example.library.core.util.AppDataUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {
	Properties properties = AppDataUtil.getRuntimeProperties("library");
	
	private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
       // return userRepository.findAll(Pageable pageable);  
    	return null;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
    	
    	String code =  (String) properties.get("registration.site.code");		
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
        long countToday = userRepository.countByCreatedAtToday(LocalDate.now());
        String registerId = "RG-" + code + datePart + "-" + (countToday + 1);
        user.setRegisterId(registerId);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        
        userRepository.save(existingUser);
    }
}
