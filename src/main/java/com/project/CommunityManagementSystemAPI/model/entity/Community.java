package com.project.CommunityManagementSystemAPI.model.entity;

import jakarta.persistence.*;
import lombok.*;

// Entity for user details
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_username", referencedColumnName = "username")
    private Profile owner;
}
