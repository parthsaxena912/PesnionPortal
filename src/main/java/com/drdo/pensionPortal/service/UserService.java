package com.drdo.pensionPortal.service;







import org.springframework.stereotype.Service;

import com.drdo.pensionPortal.entity.User;
import com.drdo.pensionPortal.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // Register user
    public User register(User user) throws Exception {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists!");
        }
        return repo.save(user);
    }

    // Login user
    public String login(String username, String password) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user.getRole(); // return USER or ADMIN
            } else {
                return "INVALID_PASSWORD";
            }
        } else {
            return "USER_NOT_FOUND";
        }
    }
}
