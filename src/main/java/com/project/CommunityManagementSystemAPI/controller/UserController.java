package com.project.CommunityManagementSystemAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CommunityManagementSystemAPI.dto.users.UserDetailsRequest;
import com.project.CommunityManagementSystemAPI.dto.users.UserDetailsResponse;
import com.project.CommunityManagementSystemAPI.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> getMyDetails(@PathVariable long id) {
        UserDetailsResponse response = service.getMyUserDetails(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDetailsResponse> updateMyDetails(@PathVariable long id,
            @Valid @RequestBody UserDetailsRequest request) {
        UserDetailsResponse response = service.updateUserDetails(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}