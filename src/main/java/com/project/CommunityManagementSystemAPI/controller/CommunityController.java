package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.community.*;
import com.project.CommunityManagementSystemAPI.service.CommunityService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

// Controller for community-related endpoints
@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
public class CommunityController {
    private final CommunityService service;

    // method for creating a community
    @PostMapping("/create")
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody @Valid CommunityRequest request) {
        CommunityResponse response = service.createCommunity(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // method for getting a community by name
    @GetMapping("/{searchByName}")
    public ResponseEntity<CommunityResponse> getCommunityByName(@PathVariable String searchByName) {
        CommunityResponse response = service.getCommunityByName(searchByName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // method for updating a community by name
    @PutMapping("/update/{communityName}")
    public ResponseEntity<CommunityResponse> updateCommunityByName(@PathVariable String communityName,
            @RequestBody @Valid CommunityRequest request) {
        CommunityResponse response = service.updateCommunityByName(communityName, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}