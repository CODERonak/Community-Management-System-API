package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.profile.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.AccessDeniedException;
import com.project.CommunityManagementSystemAPI.exceptions.custom.AlreadyExistsException;
import com.project.CommunityManagementSystemAPI.exceptions.custom.NotFoundException;
import com.project.CommunityManagementSystemAPI.mappers.ProfileMapper;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.ProfileRepository;
import com.project.CommunityManagementSystemAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

// Service for user profile
@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper mapper;

    // method to create a new profile
    public ProfileResponse createProfile(ProfileRequest request) {

        // check if user already has a profile
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found, please register first"));

        // check if username already exists
        if (profileRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException("Username has already been taken by someone else, please choose another");
        }

        // check if user exists
        if (userRepository.findById(request.getUserId()).isEmpty()) {
            throw new AccessDeniedException("You are not authorized to access the page, register first");
        }

        // check if user already has a profile
        if (profileRepository.findByUser(user).isPresent()) {
            throw new AlreadyExistsException("You already have a profile");
        }

        // create a new profile
        Profile profile = mapper.toEntity(request, user);
        profileRepository.save(profile);
        return mapper.toDto(profile);
    }

    // method to get a user's profile with username
    public ProfileResponse getProfile(String username) {

        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return mapper.toDto(profile);
    }

    // method to update a user's profile with username
    public ProfileResponse updateProfile(String username, ProfileRequest request) {

        // get the authenticated user
        Users authenticatedUser = getAuthenticatedUser();

        // check if the authenticated user has a profile
        Profile profileToUpdate = profileRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        if (authenticatedUser.getId() != profileToUpdate.getUser().getId()) {
            throw new AccessDeniedException("You are not authorized to update this profile.");
        }

        // update the profile
        profileToUpdate.setUsername(request.getUsername());
        profileToUpdate.setDateOfBirth(request.getDateOfBirth());
        profileToUpdate.setCity(request.getCity());
        profileToUpdate.setBio(request.getBio());

        Profile saved = profileRepository.save(profileToUpdate);
        return mapper.toDto(saved);
    }

    // method to get the authenticated user
    private Users getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = auth.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
