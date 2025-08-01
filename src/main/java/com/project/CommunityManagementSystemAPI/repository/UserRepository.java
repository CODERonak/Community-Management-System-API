package com.project.CommunityManagementSystemAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    <Optional> Users findByEmail(String email);

    boolean existsByEmail(String email);
}