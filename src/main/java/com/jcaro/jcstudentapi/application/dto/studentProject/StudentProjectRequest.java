package com.jcaro.jcstudentapi.application.dto.studentProject;


import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import jakarta.validation.constraints.NotNull;

/**
 * DTO used to submit a student's evaluation for a project.
 * <p>
 * Groups all the scoring parameters into a single object
 * instead of passing them as multiple request parameters.
 */
public record StudentProjectRequest(

        @NotNull(message = "Code quality score is required")
        String codeQuality,

        @NotNull(message = "Functionality score is required")
        String functionality,

        @NotNull(message = "Security score is required")
        String security,

        @NotNull(message = "Documentation score is required")
        String documentation,

        @NotNull(message = "Deployment score is required")
        String deployment,

        @NotNull(message = "Extra score is required")
        String extra
) {}
