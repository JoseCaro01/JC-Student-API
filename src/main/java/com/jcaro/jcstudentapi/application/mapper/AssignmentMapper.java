package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;

import java.util.Collections;
import java.util.List;

/**
 * Mapper for converting between AssignmentRequest DTO and Assignment domain model.
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
        List<StudentAssignment> studentAssignments = Collections.emptyList(); // No assignments initially
        return new Assignment(
                null, // id is null for new Assignment, assigned by persistence layer
                request.name(),
                request.description(),
                request.obligatory(),
                course,
                studentAssignments
        );
    }


}
