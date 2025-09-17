package com.jcaro.jcstudentapi.application.dto.user;

import com.jcaro.jcstudentapi.domain.model.Role;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * DTO used to return User information to API clients.
 * <p>
 * Password is intentionally omitted to prevent exposing sensitive data.
 * Includes roles and account creation timestamp.
 */
public record UserResponse(
        Long id,
        String name,
        String lastName,
        String email,
        LocalDateTime creationDate,
        Collection<Role> roles
) {}
