package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.users.*;
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

   public UserDetailsResponse getMyUserDetails(long userId) {
      Users user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

      return mapper.toDto(user);
   }

   public UserDetailsResponse updateUserDetails(long userId, UserDetailsRequest request) {
      Users user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

      user.setEmail(request.getEmail());
      user.setPassword(passwordEncoder.encode(request.getPassword()));

      Users updatedUser = userRepository.save(user);

      return mapper.toDto(updatedUser);
   }

}
