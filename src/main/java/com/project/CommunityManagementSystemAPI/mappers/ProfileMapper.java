package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CommunityManagementSystemAPI.dto.profile.*;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    // Maps ProfileRequest to Profile entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user") // Maps user field
    Profile toEntity(ProfileRequest request, Users user);

    @Mapping(source = "user.id", target = "userId") // it maps the user.id to userId
    ProfileResponse toDto(Profile profile);
}