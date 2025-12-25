package com.example.library.book.controller;
import com.example.library.book.entity.User;
import com.example.library.book.service.UserService;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/api/users")
public class OldUserController {
    private final UserService userService;

    public OldUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
    	
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
