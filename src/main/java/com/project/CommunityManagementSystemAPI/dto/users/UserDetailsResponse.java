package com.project.CommunityManagementSystemAPI.dto.users;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private long id;
    private String email;
    private Role role;
}
