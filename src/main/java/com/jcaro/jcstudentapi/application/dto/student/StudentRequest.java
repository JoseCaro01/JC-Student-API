package com.jcaro.jcstudentapi.application.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * Data Transfer Object (DTO) for creating or updating a Student.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending student-related requests.
 */
public record StudentRequest(

        @NotBlank(message = "Student name cannot be blank")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotEmpty(message = "At least one course ID must be provided")
        List<Long> coursesId
) {
}
