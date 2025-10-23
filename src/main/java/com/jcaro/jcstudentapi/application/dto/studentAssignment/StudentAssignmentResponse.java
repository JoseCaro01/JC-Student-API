package com.jcaro.jcstudentapi.application.dto.studentAssignment;

import com.jcaro.jcstudentapi.domain.model.ScoreEnum;

/**
 * Data Transfer Object (DTO) for get StudentAssignment.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending student-assignment-related requests.
 * */
public record StudentAssignmentResponse(Long id,
                                        Long studentId,
                                        Long assignmentId,
                                        int score) {

}
