package com.jcaro.jcstudentapi.application.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO used to create or update a Course.
 * Contains the data required to construct a valid Course in the application layer.
 * Validation annotations ensure required fields are present and correct.
 */
public record CourseRequest(
        @NotBlank(message = "Course name cannot be blank")
        String name,
        @NotBlank(message = "Course programming language cannot be blank")
        String programmingLanguage
) {}