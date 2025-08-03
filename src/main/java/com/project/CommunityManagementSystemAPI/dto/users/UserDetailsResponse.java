package com.project.CommunityManagementSystemAPI.dto.users;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import lombok.*;

// DTO for user details with id, email, password and role
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private long id;
    private String email;
    private String password;
    private Role role;
}
