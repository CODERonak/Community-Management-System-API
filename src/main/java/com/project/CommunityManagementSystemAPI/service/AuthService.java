package com.project.CommunityManagementSystemAPI.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.auth.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.auth.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.user.UserNotFoundException;
import com.project.CommunityManagementSystemAPI.jwt.JWTUtil;
import com.project.CommunityManagementSystemAPI.mappers.AuthMapper;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

// Service for user authentication
@Service
@AllArgsConstructor // constructor injection
public class AuthService {
    // fields to be injected
    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    // method to register a new user
    public RegisterResponse register(RegisterRequest request) {
        // create's a new user entity from the request
        Users user = mapper.toEntity(request);

        // encodes the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // saves the user to the database
        try {
            userRepository.save(user);
            // if successful, returns the user's details
        } catch (DataIntegrityViolationException e) {
            // if email already exists, throws an exception
            throw new EmailExistsException("Email already exists");
        }

        // returns the user's details
        return mapper.toDto(user);
    }

    // method to authenticate a user
    public LoginResponse login(LoginRequest request) {

        // authenticates the user with the email and password and jwt token
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

                            // sets the authentication
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateToken(request.getEmail());

            // finds the user by email
            Users loggedInUser = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            LoginResponse response = mapper.toLoginResponse(loggedInUser);

            response.setToken(jwtToken);

            return response;

            // if authentication fails, throws an exception
        } catch (BadCredentialsException e) {
            throw new LoginFailedException("Invalid email or password");
        }
    }
}