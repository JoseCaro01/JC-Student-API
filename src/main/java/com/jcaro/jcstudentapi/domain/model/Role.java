package com.jcaro.jcstudentapi.domain.model;

/**
 * Represents a user's role within the application.
 * This is a core domain model, characterized by immutability and simple validation.
 * As a Java record, it automatically provides a constructor, accessors (getters),
 * equals(), hashCode(), and toString() methods.
 *
 * @param id   The unique identifier of the role.
 * @param name The name of the role (e.g., "ROLE_ADMIN", "ROLE_USER").
 */
public record Role(
        Long id,
        String name
) {
    /**
     * Compact constructor for the Role record.
     * It ensures that the role name is not null or blank upon creation.
     *
     * @throws IllegalArgumentException if the name is empty or null.
     */
    public Role {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }
    }
}