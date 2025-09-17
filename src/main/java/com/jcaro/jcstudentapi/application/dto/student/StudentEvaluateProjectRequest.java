package com.jcaro.jcstudentapi.application.dto.student;


import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import jakarta.validation.constraints.NotNull;

/**
 * DTO used to submit a student's evaluation for a project.
 * <p>
 * Groups all the scoring parameters into a single object
 * instead of passing them as multiple request parameters.
 */
public record StudentEvaluateProjectRequest(

        @NotNull(message = "Code quality score is required")
        ScoreEnum codeQuality,

        @NotNull(message = "Functionality score is required")
        ScoreEnum functionality,

        @NotNull(message = "Security score is required")
        ScoreEnum security,

        @NotNull(message = "Documentation score is required")
        ScoreEnum documentation,

        @NotNull(message = "Deployment score is required")
        ScoreEnum deployment,

        @NotNull(message = "Extra score is required")
        ScoreEnum extra
) {}
