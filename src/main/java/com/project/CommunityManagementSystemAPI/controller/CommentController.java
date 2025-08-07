package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.comment.*;
import com.project.CommunityManagementSystemAPI.service.CommentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/community/")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request) {

        CommentResponse response = commentService.createComment(postId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}