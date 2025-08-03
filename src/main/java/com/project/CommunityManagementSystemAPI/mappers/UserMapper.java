package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.*;

import com.project.CommunityManagementSystemAPI.dto.users.*;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Map UserDetailsRequest to Users entity
    @Mapping(target = "id", ignore = true)
    Users toEntity(UserDetailsRequest request);

    // Map Users entity to UserDetailsResponse
    UserDetailsResponse toDto(Users entity);
}