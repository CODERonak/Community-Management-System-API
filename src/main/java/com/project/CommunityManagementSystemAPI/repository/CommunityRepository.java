package com.project.CommunityManagementSystemAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.CommunityManagementSystemAPI.model.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    
}