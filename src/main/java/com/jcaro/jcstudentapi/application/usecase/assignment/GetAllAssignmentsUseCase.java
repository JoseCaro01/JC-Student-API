package com.jcaro.jcstudentapi.application.usecase.assignment;

import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Use case for retrieving assignments, optionally filtered by course.
 */
public class GetAllAssignmentsUseCase {

    private final AssignmentRepository assignmentRepository;

    public GetAllAssignmentsUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Retrieves all assignments or those belonging to a specific course.
     *
     * @param courseId optional filter by courseId
     * @return list of assignments
     */
    public List<Assignment> execute(Long courseId) {

        return courseId !=null
                ? assignmentRepository.findByCourseId(courseId)
                : assignmentRepository.findAll();
    }
}
