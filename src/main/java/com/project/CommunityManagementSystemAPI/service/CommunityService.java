package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.community.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.AccessDeniedException;
import com.project.CommunityManagementSystemAPI.exceptions.custom.NotFoundException;
import com.project.CommunityManagementSystemAPI.mappers.CommunityMapper;
import com.project.CommunityManagementSystemAPI.model.entity.Community;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.*;

import lombok.AllArgsConstructor;

// Service for community-related operations
@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CommunityMapper mapper;

    // method for creating a community
    public CommunityResponse createCommunity(CommunityRequest request) {
        Users authenticatedUser = getAuthenticatedUser();

        // Check if the authenticated user has a profile
        Profile owner = profileRepository.findByUser(authenticatedUser)
                .orElseThrow(() -> new NotFoundException("You don't have a profile, please create one."));

        // Create a new community entity
        Community community = mapper.toEntity(request, owner);
        Community savedCommunity = communityRepository.save(community);
        return mapper.toResponse(savedCommunity);
    }

    // method for getting a community by name
    public CommunityResponse getCommunityByName(String communityName) {
        // Check if the authenticated user has a profile
        Community community = communityRepository.findByName(communityName)
                .orElseThrow(() -> new NotFoundException("Community not found."));

        return mapper.toResponse(community);
    }

    // method for updating a community by name
    public CommunityResponse updateCommunityByName(String communityName, CommunityRequest request) {
        Users authenticatedUser = getAuthenticatedUser();

        // Check if the community exists
        Community community = communityRepository.findByName(communityName)
                .orElseThrow(() -> new NotFoundException("Community not found."));

        // Check if the authenticated user is the owner of the community if not throws
        // exception
        if (community.getOwner().getUser().getId() != authenticatedUser.getId()) {
            throw new AccessDeniedException("You are not authorized to update this community.");
        }

        community.setName(request.getName());
        community.setDescription(request.getDescription());

        Community updatedCommunity = communityRepository.save(community);
        return mapper.toResponse(updatedCommunity);
    }

    // Helper method to get the authenticated user
    private Users getAuthenticatedUser() {
        // Get the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = auth.getName();

        // Find the user by email
        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new NotFoundException("Authenticated user not found."));
    }

}