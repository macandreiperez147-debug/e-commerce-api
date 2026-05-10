package com.ws101.perez.ecommerceapi.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws101.perez.ecommerceapi.model.User;
import com.ws101.perez.ecommerceapi.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository repository,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {

        // HASH PASSWORD (required for login to work)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // DEFAULT ROLE FIX (prevents 400 + DB error)
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        return repository.save(user);
    }
}