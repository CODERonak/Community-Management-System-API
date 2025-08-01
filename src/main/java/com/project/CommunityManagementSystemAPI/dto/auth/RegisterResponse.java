package com.project.CommunityManagementSystemAPI.dto.auth;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private long userId;
    private String email;
    private Role role;
}