package com.project.CommunityManagementSystemAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.CommunityManagementSystemAPI.model.entity.Profile;
import com.project.CommunityManagementSystemAPI.model.entity.Users;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Profile> findByUser(Users user);

    List<Profile> findByCity(String city);
}