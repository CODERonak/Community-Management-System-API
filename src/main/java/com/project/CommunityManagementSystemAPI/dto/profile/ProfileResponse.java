package com.project.CommunityManagementSystemAPI.dto.profile;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for user profile with validation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
    private long userId;
    private String username;
    private LocalDate dateOfBirth;
    private String city;
    private String bio;
}