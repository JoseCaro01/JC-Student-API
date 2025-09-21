package com.jcaro.jcstudentapi.application.usecase.assignment;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;

/**
 * Use case for creating a new Assignment.
 */
public class CreateAssignmentUseCase {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;

    public CreateAssignmentUseCase(AssignmentRepository assignmentRepository, CourseRepository courseRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.assignmentMapper = assignmentMapper;
    }

    /**
     * Creates a new assignment ensuring its course exists.
     *
     * @param assignment Assignment to create
     * @return the persisted Assignment
     * @throws CourseNotFoundException if course is null or doesn't exist
     */
    public Assignment execute(AssignmentRequest assignment) {


        if (assignment.courseId() == null) {
            throw new CourseNotFoundException(-1L);
        }
        final Course course = courseRepository.findById(assignment.courseId()).orElseThrow(() -> new CourseNotFoundException(assignment.courseId()));
        final Assignment newAssignment = assignmentMapper.requestToDomain(assignment,course);
        return assignmentRepository.save(newAssignment);
    }
}
