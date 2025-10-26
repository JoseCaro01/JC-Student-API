package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentRequest;
import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentResponse;
import com.jcaro.jcstudentapi.domain.model.*;

/**
 * Mapper for converting between Request and Response DTOs and the Project domain model.
 * <p>
 * This class belongs to the application layer and ensures separation of concerns
 * by transforming input data into the domain model without exposing infrastructure details.
 */
public class StudentAssignmentMapper {


    /**
     * Converts a StudentAssignment domain object into a StudentAssignmentResponse DTO.
     * <p>
     *
     * @param studentAssignment the Course domain object
     * @return a StudentAssignmentResponse DTO for returning to API clients
     */
    public StudentAssignmentResponse domainToStudentAssignmentResponse(StudentAssignment studentAssignment) {
        return new StudentAssignmentResponse(
                studentAssignment.id(),
                studentAssignment.student().id(),
                studentAssignment.assignment().id(),
                studentAssignment.score().getValue());
    }

    /**
     * Converts an AssignmentRequest DTO to an Assignment domain object.
     *
     * @param student    the Student entity to associate with the StudentAssignment
     * @param assignment the Assignment entity to associate with the StudentAssignment
     * @param request    the StudentAssignmentRequest DTO received from the API
     * @return a new Assignment domain object
     */
    public StudentAssignment requestToDomain(Student student, Assignment assignment, StudentAssignmentRequest request) {
        ScoreEnum scoreEnum = ScoreEnum.fromValue(request.score());

        return new StudentAssignment(
                null,
                student,
                assignment,
                scoreEnum

        );
    }
}
