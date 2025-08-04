package com.project.CommunityManagementSystemAPI.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.CommunityManagementSystemAPI.exceptions.ErrorResponse;
import com.project.CommunityManagementSystemAPI.exceptions.custom.auth.LoginFailedException;

// Global exception handler for custom exceptions
@RestControllerAdvice
public class GlobalExceptionHandler {

        // Handling not found exception while it checks for the existence of an entity
        // in the
        // database
        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFoundException(
                        NotFoundException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // Handling Acess Denied exception for when a user tries to access a endpoint
        // they don't have access to
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> handleAcessDeniedException(
                        AccessDeniedException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.FORBIDDEN.value(),
                                HttpStatus.FORBIDDEN.getReasonPhrase(),
                                ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        // Handling Already Exists exception for when a entity already exists in the
        // database
        @ExceptionHandler(AlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleAlreadyExistsException(
                        AlreadyExistsException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.getReasonPhrase(),
                                ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        // Handling Login Failed exception for when a user tries to login with invalid
        // credentials
        @ExceptionHandler(LoginFailedException.class)
        public ResponseEntity<ErrorResponse> handleLoginFailedException(
                        LoginFailedException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.UNAUTHORIZED.value(),
                                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                                ex.getMessage());
                return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
}