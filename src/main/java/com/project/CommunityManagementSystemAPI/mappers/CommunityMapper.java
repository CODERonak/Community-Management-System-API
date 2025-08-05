package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CommunityManagementSystemAPI.dto.community.*;
import com.project.CommunityManagementSystemAPI.model.entity.Community;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "id", ignore = true)
    Community toEntity(CommunityRequest request, Profile owner);

    @Mapping(target = "ownerUsername", source = "owner.username")
    CommunityResponse toResponse(Community community);
}