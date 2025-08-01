package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.*;

import com.project.CommunityManagementSystemAPI.dto.auth.RegisterRequest;
import com.project.CommunityManagementSystemAPI.dto.auth.RegisterResponse;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    Users toEntity(RegisterRequest request);

    @Mapping(target = "userId", source = "id")
    RegisterResponse toDto(Users entity);
}
