package com.jcaro.jcstudentapi.application.usecase.assignment;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

/**
 * Use case for editing an existing Assignment.
 */
public class EditAssignmentUseCase {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;

    public EditAssignmentUseCase(AssignmentRepository assignmentRepository,
                                 CourseRepository courseRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.assignmentMapper = assignmentMapper;
    }

    /**
     * Updates an existing assignment if both the assignment and its course exist.
     *
     * @param assignment Assignment to update
     * @return the updated Assignment
     * @throws AssignmentNotFoundException if assignment is invalid
     * @throws CourseNotFoundException     if course is invalid
     */
    public AssignmentResponse execute(Long id, AssignmentRequest assignment) {
        if (assignmentRepository.findById(id).isEmpty()) {
            throw new AssignmentNotFoundException(id);
        }
        if (assignment.courseId() == null) {
            throw new CourseNotFoundException(-1L);
        }

        final Course course = courseRepository.findById(assignment.courseId()).orElseThrow(() -> new CourseNotFoundException(assignment.courseId()));

        return assignmentMapper.domainToAssignmentResponse(assignmentRepository.save(assignmentMapper.requestToDomain(assignment, course).withId(id)));

    }
}
