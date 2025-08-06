package com.project.CommunityManagementSystemAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.CommunityManagementSystemAPI.model.entity.Community;
import com.project.CommunityManagementSystemAPI.model.entity.Profile;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // Custom query to find a community by name
    Optional<Community> findByName(String name);

    // Custom query to find a community by owner's username
    Optional<Community> findByOwner(Profile owner);
}