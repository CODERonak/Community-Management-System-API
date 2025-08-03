package com.project.CommunityManagementSystemAPI.dto.users;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import jakarta.validation.constraints.*;
import lombok.*;

// DTO for user registration with email, password and role validation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Role cannot be null")
    private Role role;
}