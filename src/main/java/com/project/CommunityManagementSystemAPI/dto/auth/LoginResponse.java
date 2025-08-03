package com.project.CommunityManagementSystemAPI.dto.auth;

import lombok.*;

// DTO for user returning token
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private long id;
    private String email;
    private String token;
}
