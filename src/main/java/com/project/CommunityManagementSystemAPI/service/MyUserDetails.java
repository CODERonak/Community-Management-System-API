package com.project.CommunityManagementSystemAPI.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.CommunityManagementSystemAPI.model.entity.Users;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private final Users user;

    // Get user's authorities ADMIN and MEMBER
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
        return authorities;
    }

    // returns password of user database
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // returns user's email from the database
    @Override
    public String getUsername() {
        return user.getEmail();
    }

}