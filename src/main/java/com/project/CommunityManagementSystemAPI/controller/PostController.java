package com.project.CommunityManagementSystemAPI.controller;

import java.util.List;

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

    @PostMapping("/{communityId}/posts/create")
    public ResponseEntity<PostResponse> createPost(
            @PathVariable long communityId,
            @RequestBody PostRequest request) {

        PostResponse response = service.createPost(communityId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{communityId}")
    public ResponseEntity<List<PostResponse>> getAllPostsByCommunity(@PathVariable long communityId) {
        List<PostResponse> response = service.getAllPostsByCommunity(communityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}