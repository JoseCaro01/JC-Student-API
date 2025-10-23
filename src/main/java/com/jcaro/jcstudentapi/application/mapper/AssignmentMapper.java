package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.course.CourseDetailedResponse;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;

import java.util.Collections;
import java.util.List;

/**
 * Mapper for converting between Request and Response DTOs and Assignment domain model.
 * <p>
 * The mapper handles the translation of simple fields and lists, but it assumes
 * that related entities (like Course) are already loaded and passed in as parameters.
 * <p>
 * Validation is handled in the domain model; this mapper does not perform business validation.
 */
public class AssignmentMapper {

    /**
     * Converts an AssignmentRequest DTO to an Assignment domain object.
     *
     * @param request the AssignmentRequest DTO received from the API
     * @param course  the Course entity to associate with the Assignment
     * @return a new Assignment domain object
     */
    public Assignment requestToDomain(AssignmentRequest request, Course course) {
        return new Assignment(
                null, // id is null for new Assignment, assigned by persistence layer
                request.name(),
                request.description(),
                request.obligatory(),
                course
        );
    }

    /**
     * Converts an Assignment domain object into a AssignmentResponse DTO.
     * <p>
     *
     * @param assignment the Course domain object
     * @return a AssignmentResponse DTO for returning to API clients
     */
    public AssignmentResponse domainToAssignmentResponse(Assignment assignment) {
        return new AssignmentResponse(
                assignment.id(),
                assignment.name(),
                assignment.description(),
                assignment.obligatory(),
                assignment.course().id()
        );
    }


}
