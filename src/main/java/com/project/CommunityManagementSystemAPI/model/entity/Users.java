package com.project.CommunityManagementSystemAPI.model.entity;

import com.project.CommunityManagementSystemAPI.model.enums.Role;

import jakarta.persistence.*;
import lombok.*;

// Entity for user details
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    // Entity fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true) // Email must be unique and not null
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Enum type for role
    private Role role;
}