package com.jcaro.jcstudentapi.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO used for creating or updating a User.
 * <p>
 * Contains the input data required by the application layer.
 * Validation annotations ensure email format is correct and password meets minimum length.
 */
public record UserRequest(
        String name,
        String lastName,
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {}
