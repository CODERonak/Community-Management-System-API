package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.users.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.user.*;
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

      Users userToAcess = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

      Users authenticatedUser = getAuthenticatedUsers();

      if (authenticatedUser.getId() != userToAcess.getId()) {
         throw new AccessDeniedException("You are not authorized to view this user's details.");
      }

      return mapper.toDto(authenticatedUser);
   }

   public UserDetailsResponse updateUserDetails(long userId, UserDetailsRequest request) {

      Users userToUpdate = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

      Users authenticatedUser = getAuthenticatedUsers();

      if (authenticatedUser.getId() != userToUpdate.getId()) {
         throw new AccessDeniedException("You are not authorized to update this user's details.");
      }

      authenticatedUser.setEmail(request.getEmail());
      authenticatedUser.setPassword(passwordEncoder.encode(request.getPassword()));

      Users updatedUser = userRepository.save(authenticatedUser);

      return mapper.toDto(updatedUser);
   }

   private Users getAuthenticatedUsers() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String authenticatedUserEmail = auth.getName();

      return userRepository.findByEmail(authenticatedUserEmail)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
   }
}