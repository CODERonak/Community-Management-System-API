package com.project.CommunityManagementSystemAPI.exceptions.custom.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for email already exists
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}