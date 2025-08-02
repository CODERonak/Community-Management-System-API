package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.*;

import com.project.CommunityManagementSystemAPI.dto.users.*;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    Users toEntity(UserDetailsRequest request);

    UserDetailsResponse toDto(Users entity);
}