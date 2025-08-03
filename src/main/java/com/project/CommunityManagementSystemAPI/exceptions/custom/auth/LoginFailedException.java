package com.project.CommunityManagementSystemAPI.exceptions.custom.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exception for invalid login details
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
}