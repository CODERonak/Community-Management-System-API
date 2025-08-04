package com.project.CommunityManagementSystemAPI.dto.profile;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for user profile with validation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    @NotNull
    private long userId;

    @NotBlank
    private String username;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String city;

    @NotBlank
    private String bio;
}