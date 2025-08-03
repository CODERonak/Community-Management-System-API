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

// Service for user-related operations
@Service
@AllArgsConstructor
public class UserService {
   // Fields to be injected
   private final UserRepository userRepository;
   private final UserMapper mapper;
   private final BCryptPasswordEncoder passwordEncoder;

   // Method to get user details by ID
   public UserDetailsResponse getMyUserDetails(long userId) {

      // Find the user by ID if not found, throw an exception
      Users userToAcess = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

      // Get the authenticated user
      Users authenticatedUser = getAuthenticatedUsers();

      // Check if the authenticated user is not the same as the user to access
      if (authenticatedUser.getId() != userToAcess.getId()) {
         throw new AccessDeniedException("You are not authorized to view this user's details.");
      }

      return mapper.toDto(authenticatedUser);
   }

   // Method to update user details
   public UserDetailsResponse updateUserDetails(long userId, UserDetailsRequest request) {

      // Find the user by ID if not found, throw an exception
      Users userToUpdate = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

      // Get the authenticated user
      Users authenticatedUser = getAuthenticatedUsers();

      // Check if the authenticated user is not the same as the user to update
      if (authenticatedUser.getId() != userToUpdate.getId()) {
         throw new AccessDeniedException("You are not authorized to update this user's details.");
      }

      // Update the user's details
      authenticatedUser.setEmail(request.getEmail());
      authenticatedUser.setPassword(passwordEncoder.encode(request.getPassword()));

      Users updatedUser = userRepository.save(authenticatedUser);

      return mapper.toDto(updatedUser);
   }

   // Helper method to get the authenticated user
   private Users getAuthenticatedUsers() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String authenticatedUserEmail = auth.getName();

      return userRepository.findByEmail(authenticatedUserEmail)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
   }
}