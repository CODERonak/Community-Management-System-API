package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.profile.*;
import com.project.CommunityManagementSystemAPI.service.ProfileService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @PostMapping("/create")
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody @Valid ProfileRequest request) {
        ProfileResponse response = service.createProfile(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String username) {
        ProfileResponse response = service.getProfile(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable String username,
            @RequestBody @Valid ProfileRequest request) {
        ProfileResponse response = service.updateProfile(username, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}