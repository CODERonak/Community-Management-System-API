package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.auth.*;
import com.project.CommunityManagementSystemAPI.mappers.UserMapper;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        Users user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return mapper.toDto(user);

    }
}