package com.project.CommunityManagementSystemAPI.exceptions;

import lombok.Data;

// DTO for error response with the status, error message and error details
@Data
public class ErrorResponse {

    private int status;
    private String error;
    private String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}