package com.project.CommunityManagementSystemAPI.dto.auth;

import jakarta.validation.constraints.*;
import lombok.*;

// DTO for user login
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    // Email validation
    @Email(message = "Invalid email format")
    private String email;

    // Password validation
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}