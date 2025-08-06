package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.post.*;
import com.project.CommunityManagementSystemAPI.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
public class PostController {
    private final PostService service;

    @PostMapping("/posts/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        PostResponse response = service.createPost(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}