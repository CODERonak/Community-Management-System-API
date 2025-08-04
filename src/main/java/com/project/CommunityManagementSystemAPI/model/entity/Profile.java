package com.project.CommunityManagementSystemAPI.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

// Entity for user details
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true) 
    private String username;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

    LocalDate dateOfBirth;

    private String city;

    private String bio;
}