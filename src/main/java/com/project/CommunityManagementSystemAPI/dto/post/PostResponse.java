package com.project.CommunityManagementSystemAPI.dto.post;

import lombok.Data;

@Data
public class PostResponse {
    private String title;
    private String content;
    private Long authorId;
    private Long communityId;
}