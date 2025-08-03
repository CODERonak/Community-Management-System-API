package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.*;

import com.project.CommunityManagementSystemAPI.dto.auth.LoginResponse;
import com.project.CommunityManagementSystemAPI.dto.auth.RegisterRequest;
import com.project.CommunityManagementSystemAPI.dto.auth.RegisterResponse;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

// Mapper for user registration and login
@Mapper(componentModel = "spring")
public interface AuthMapper {
    // Map RegisterRequest to Users entity
    @Mapping(target = "id", ignore = true)
    Users toEntity(RegisterRequest request);

    // Map Users entity to RegisterResponse
    RegisterResponse toDto(Users entity);

    // Map Users entity to LoginResponse
    @Mapping(target = "token", ignore = true)
    LoginResponse toLoginResponse(Users user);
}
