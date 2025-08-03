package com.project.CommunityManagementSystemAPI.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.project.CommunityManagementSystemAPI.exceptions.custom.user.UserNotFoundException;
import com.project.CommunityManagementSystemAPI.model.entity.Users;
import com.project.CommunityManagementSystemAPI.repository.UserRepository;

import lombok.AllArgsConstructor;

// Service for user details
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // loads user details from the database
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email not found with username: " + email));

        return new MyUserDetails(user);

    }

}