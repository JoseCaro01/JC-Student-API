package com.jcaro.jcstudentapi.application.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for creating or updating a Project.
 */
public record ProjectRequest(
        @NotBlank(message = "Project name cannot be blank")
        String name,

        String description,

        @NotNull(message = "Course ID must be provided")
        Long courseId
) {}
