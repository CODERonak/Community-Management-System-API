package com.project.CommunityManagementSystemAPI.dto.auth;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import lombok.*;

// DTO for user registration response with id, email and role
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private long id;
    private String email;
    private Role role;
}