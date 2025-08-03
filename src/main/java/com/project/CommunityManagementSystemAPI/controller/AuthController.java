package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.auth.*;
import com.project.CommunityManagementSystemAPI.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

// Controller for user authentication
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService service;

    // Register a new user and return the created user's details
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = service.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Authenticate a user and return a JWT token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = service.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}