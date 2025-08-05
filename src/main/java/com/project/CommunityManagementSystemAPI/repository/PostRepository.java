package com.project.CommunityManagementSystemAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.CommunityManagementSystemAPI.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorUsername(String username);

    List<Post> findByCommunityId(Long communityId);

}