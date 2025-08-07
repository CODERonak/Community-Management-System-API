package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.dto.comment.*;
import com.project.CommunityManagementSystemAPI.exceptions.custom.NotFoundException;
import com.project.CommunityManagementSystemAPI.mappers.CommentMapper;
import com.project.CommunityManagementSystemAPI.model.entity.*;
import com.project.CommunityManagementSystemAPI.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    // method for creating a comment
    public CommentResponse createComment(Long postId, CommentRequest request) {

        // Find the author
        Users user = getAuthenticatedUser();

        // Check if the author has a profile
        Profile author = profileRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("You don't have a profile, please create one."));

        // Find the post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found."));

        // Map and save comment
        Comment comment = commentMapper.toEntity(request, author, post);
        Comment saved = commentRepository.save(comment);

        return commentMapper.toResponse(saved);
    }

    // Helper method to get the authenticated user
    private Users getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Authenticated user not found."));
    }
}