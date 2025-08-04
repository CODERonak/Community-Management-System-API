package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.profile.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.user.AccessDeniedException;
import com.project.CommunityManagementSystemAPI.exceptions.custom.user.UserNotFoundException;
import com.project.CommunityManagementSystemAPI.mappers.ProfileMapper;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.ProfileRepository;
import com.project.CommunityManagementSystemAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper mapper;

    public ProfileResponse createProfile(ProfileRequest request) {

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found, please register first"));

        Profile profile = mapper.toEntity(request, user);
        profileRepository.save(profile);
        return mapper.toDto(profile);
    }

    public ProfileResponse getProfile(String username) {

        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Profile not found"));

        return mapper.toDto(profile);
    }

    public ProfileResponse updateProfile(String username, ProfileRequest request) {

        Users authenticatedUser = getAuthenticatedUser();

        Profile profileToUpdate = profileRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Profile not found"));

        if (authenticatedUser.getId() != profileToUpdate.getUser().getId()) {
            throw new AccessDeniedException("You are not authorized to update this profile.");
        }

        profileToUpdate.setUsername(request.getUsername());
        profileToUpdate.setDateOfBirth(request.getDateOfBirth());
        profileToUpdate.setCity(request.getCity());
        profileToUpdate.setBio(request.getBio());

        Profile saved = profileRepository.save(profileToUpdate);
        return mapper.toDto(saved);
    }

    private Users getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = auth.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
