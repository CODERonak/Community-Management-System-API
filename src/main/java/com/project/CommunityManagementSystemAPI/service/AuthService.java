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

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public RegisterResponse register(RegisterRequest request) {
        Users user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailExistsException("Email already exists");
        }

        return mapper.toDto(user);
    }

    public LoginResponse login(LoginRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateToken(request.getEmail());

            Users loggedInUser = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            LoginResponse response = mapper.toLoginResponse(loggedInUser);

            response.setToken(jwtToken);

            return response;
        } catch (BadCredentialsException e) {
            throw new LoginFailedException("Invalid email or password");
        }
    }
}