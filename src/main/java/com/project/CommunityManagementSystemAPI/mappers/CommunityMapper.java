package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CommunityManagementSystemAPI.dto.community.*;
import com.project.CommunityManagementSystemAPI.model.entity.Community;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

// Mapper for community-related operations
@Mapper(componentModel = "spring")
public interface CommunityMapper {

    // Mapping methods for community-related operations
    @Mapping(target = "owner", source = "owner") // Map the owner field
    @Mapping(target = "id", ignore = true)
    Community toEntity(CommunityRequest request, Profile owner); // Map the request to an entity

    // Map the entity to a response
    @Mapping(target = "ownerUsername", source = "owner.username") // Map the owner's username
    CommunityResponse toResponse(Community community);
}