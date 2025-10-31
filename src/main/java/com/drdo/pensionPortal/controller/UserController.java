package com.drdo.pensionPortal.controller;



import org.springframework.web.bind.annotation.*;

import com.drdo.pensionPortal.entity.User;
import com.drdo.pensionPortal.service.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Register endpoint
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            service.register(user);
            return "User registered successfully!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.login(user.getUsername(), user.getPassword());
    }
}
