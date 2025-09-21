package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Project;

import java.util.Collections;

/**
 * Mapper for converting between ProjectRequest DTO and the Project domain model.
 * <p>
 * This class belongs to the application layer and ensures separation of concerns
 * by transforming input data into the domain model without exposing infrastructure details.
 */
public class ProjectMapper {

    /**
     * Converts a ProjectRequest DTO into a Project domain object.
     * <p>
     * Only minimal fields are set. The course reference must be resolved
     * separately using the courseId before persisting.
     *
     * @param dto    the ProjectRequest DTO containing input data
     * @param course the Course domain object resolved from courseId
     * @return a new Project domain object
     */
    public Project requestToDomain(ProjectRequest dto, Course course) {
        return new Project(
                null,                  // id is generated later by persistence
                dto.name(),
                dto.description(),
                course
        );
    }
}
