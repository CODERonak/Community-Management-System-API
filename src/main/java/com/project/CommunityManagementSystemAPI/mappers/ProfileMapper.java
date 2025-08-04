package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.google.api.services.sqladmin.SQLAdmin.Users;
import com.project.CommunityManagementSystemAPI.dto.profile.*;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    Profile toEntity(ProfileRequest request, Users user);

    @Mapping(source = "user.id", target = "userId") 
    ProfileResponse toDto(Profile profile);
}