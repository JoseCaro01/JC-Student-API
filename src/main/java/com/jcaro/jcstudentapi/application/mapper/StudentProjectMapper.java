package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.studentProject.StudentProjectResponse;
import com.jcaro.jcstudentapi.domain.model.StudentProject;

/**
 * Mapper for converting between Request and Response DTOs and the StudentProject domain model.
 * <p>
 * This class belongs to the application layer and ensures separation of concerns
 * by transforming input data into the domain model without exposing infrastructure details.
 */
public class StudentProjectMapper {


    /**
     * Converts a StudentProject domain object into a StudentProjectResponse DTO.
     * <p>
     *
     * @param studentProject the Course domain object
     * @return a StudentProjectResponse DTO for returning to API clients
     */
    public StudentProjectResponse domainToStudentProjectResponse(StudentProject studentProject) {
        return new StudentProjectResponse(
                studentProject.id(),
                studentProject.student().id(),
                studentProject.project().id(),
                studentProject.codeQuality().getValue(),
                studentProject.functionality().getValue(),
                studentProject.security().getValue(),
                studentProject.documentation().getValue(),
                studentProject.deployment().getValue(),
                studentProject.extra().getValue()
        );
    }
}
