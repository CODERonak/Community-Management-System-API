package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CommunityManagementSystemAPI.dto.community.*;
import com.project.CommunityManagementSystemAPI.service.CommunityService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
public class CommunityController {
    private final CommunityService service;

    @PostMapping("/create")
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody @Valid CommunityRequest request) {
        CommunityResponse response = service.createCommunity(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}