package com.project.CommunityManagementSystemAPI.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private long id;
    private String email;
    private String token;
}
