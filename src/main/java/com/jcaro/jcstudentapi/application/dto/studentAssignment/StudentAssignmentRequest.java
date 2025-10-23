package com.jcaro.jcstudentapi.application.dto.studentAssignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * DTO used to create or update an evaluation of an assignment.
 * Contains the partial data required to construct a valid StudentAssignment in the application layer.
 * Validation annotations ensure required fields are present and correct.
 */
public record StudentAssignmentRequest(
        @NotNull(message = "Assignment ID must be provided")
        Long assignmentId,
        @NotBlank(message = "Score cannot be empty")
        String score
) {
}
