package com.jcaro.jcstudentapi.application.dto.assignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO used to create or update an Assignment.
 * Contains the data required to construct a valid Assignment in the application layer.
 * Validation annotations ensure required fields are present and correct.
 */
public record AssignmentRequest(
        @NotBlank(message = "Assignment name cannot be empty") String name,
        String description,
        boolean obligatory,
        @NotNull(message = "Course ID must be provided") Long courseId
) {}
