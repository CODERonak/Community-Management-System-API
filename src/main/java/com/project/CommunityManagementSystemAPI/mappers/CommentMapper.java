package com.project.CommunityManagementSystemAPI.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CommunityManagementSystemAPI.dto.comment.*;
import com.project.CommunityManagementSystemAPI.model.entity.Comment;
import com.project.CommunityManagementSystemAPI.model.entity.Post;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "content", source = "request.content")
    Comment toEntity(CommentRequest request, Profile author, Post post);

    @Mapping(target = "id", source = "comment.id")
    @Mapping(target = "authorId", source = "comment.author.id")
    @Mapping(target = "postId", source = "comment.post.id")
    CommentResponse toResponse(Comment comment);
}