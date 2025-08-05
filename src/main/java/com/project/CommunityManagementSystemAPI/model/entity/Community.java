package com.project.CommunityManagementSystemAPI.model.entity;

import jakarta.persistence.*;
import lombok.*;

// Entity for community details
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

    // Many-to-one relationship with Profile entity for owner
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_username", referencedColumnName = "username")
    private Profile owner;
}
