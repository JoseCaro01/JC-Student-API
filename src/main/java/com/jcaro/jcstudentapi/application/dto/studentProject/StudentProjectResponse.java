package com.jcaro.jcstudentapi.application.dto.studentProject;

import com.jcaro.jcstudentapi.domain.model.ScoreEnum;

/**
 * Data Transfer Object (DTO) for get StudentProject.
 * <p>
 * This class belongs to the Application layer and defines the data structure
 * that the API clients must provide when sending student-project-related requests.
 * */
public record StudentProjectResponse(Long id,
                                     Long studentId,
                                     Long projectId,
                                     int codeQuality,
                                     int functionality,
                                     int security,
                                     int documentation,
                                     int deployment,
                                     int extra) {
}
