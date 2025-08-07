package com.project.CommunityManagementSystemAPI.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CommunityManagementSystemAPI.dto.post.*;
import com.project.CommunityManagementSystemAPI.model.entity.Community;
import com.project.CommunityManagementSystemAPI.model.entity.Post;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

// Mapper for post-related operations
@Mapper(componentModel = "spring")
public interface PostMapper {
    // Mapping methods for post-related operations
    @Mapping(target = "author", source = "author")
    @Mapping(target = "community", source = "community")
    @Mapping(target = "id", ignore = true)
    Post toEntity(PostRequest request, Profile author, Community community);

    // Map the entity to a response
    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "authorId", source = "post.author.id")
    @Mapping(target = "communityId", source = "post.community.id")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);
}
