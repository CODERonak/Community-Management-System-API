package com.project.CommunityManagementSystemAPI.dto.post;

import lombok.Data;

// DTO for post response
@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long communityId;
}