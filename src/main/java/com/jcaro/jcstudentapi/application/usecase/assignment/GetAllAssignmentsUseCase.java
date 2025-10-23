package com.jcaro.jcstudentapi.application.usecase.assignment;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Use case for retrieving assignments, optionally filtered by course.
 */
public class GetAllAssignmentsUseCase {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;


    public GetAllAssignmentsUseCase(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    /**
     * Retrieves all assignments or those belonging to a specific course.
     *
     * @param courseId optional filter by courseId
     * @return list of assignments
     */
    public List<AssignmentResponse> execute(Long courseId) {

        final List<Assignment> assignments=    courseId !=null
                ?  assignmentRepository.findByCourseId(courseId)
                : assignmentRepository.findAll();

        return assignments.stream().map(assignmentMapper::domainToAssignmentResponse).toList();
    }
}
