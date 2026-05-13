package com.ws101.perez.ecommerceapi.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ws101.perez.ecommerceapi.dto.RegisterUserDto;
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
    public User register(
            @Valid @RequestBody RegisterUserDto dto) {

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setEmail(dto.getEmail());

        user.setPhoneNumber(dto.getPhoneNumber());

        // DEFAULT ROLE
        if (dto.getRole() == null || dto.getRole().isBlank()) {
            user.setRole("USER");
        } else {
            user.setRole(dto.getRole());
        }

        return repository.save(user);
    }
}