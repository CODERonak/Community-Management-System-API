package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.*;

import com.project.CommunityManagementSystemAPI.dto.auth.LoginResponse;
import com.project.CommunityManagementSystemAPI.dto.auth.RegisterRequest;
import com.project.CommunityManagementSystemAPI.dto.auth.RegisterResponse;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    Users toEntity(RegisterRequest request);

    RegisterResponse toDto(Users entity);

    @Mapping(target = "token", ignore = true)
    LoginResponse toLoginResponse(Users user);
}
