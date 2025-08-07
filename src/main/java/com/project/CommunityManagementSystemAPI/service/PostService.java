package com.project.CommunityManagementSystemAPI.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.post.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.NotFoundException;
import com.project.CommunityManagementSystemAPI.mappers.PostMapper;
import com.project.CommunityManagementSystemAPI.model.entity.*;
import com.project.CommunityManagementSystemAPI.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final PostMapper mapper;

    public PostResponse createPost(long communityId, PostRequest request) {
        Users authenticatedUser = getAuthenticatedUser();

        // Check if the authenticated user has a profile
        Profile author = profileRepository.findByUser(authenticatedUser)
                .orElseThrow(() -> new NotFoundException("You don't have a profile, please create one."));

        // Get the community by ID instead of by owner
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new NotFoundException("Community not found."));

        Post post = mapper.toEntity(request, author, community);
        Post savedPost = postRepository.save(post);

        return mapper.toResponse(savedPost);
    }

    // methods for Get all posts by username
    public List<PostResponse> getAllPostsByCommunity(long communityId) {

        // Check if the community exists
        communityRepository.findById(communityId)
                .orElseThrow(() -> new NotFoundException("Community not found."));

        // Fetch posts by community ID
        List<Post> posts = postRepository.findByCommunityId(communityId);
        return mapper.toResponseList(posts);
    }

    // methods for getting all posts by the user
    private Users getAuthenticatedUser() {
        // Get the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = auth.getName();

        // Find the user by email
        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new NotFoundException("Authenticated user not found."));
    }
}