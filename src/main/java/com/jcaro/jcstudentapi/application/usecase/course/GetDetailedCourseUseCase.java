package com.jcaro.jcstudentapi.application.usecase.course;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.course.CourseDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.application.mapper.CourseMapper;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.domain.model.*;
import com.jcaro.jcstudentapi.domain.repository.*;

import java.util.List;

import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Use case to retrieve all courses.
 * <p>
 * This class encapsulates the business logic for getting all courses
 * from the repository. It does not perform any validation or
 * transformation; it simply delegates to the CourseRepository.
 */
public class GetDetailedCourseUseCase {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final ProjectRepository projectRepository;
    private final CourseMapper courseMapper;
    private final AssignmentMapper assignmentMapper;
    private final ProjectMapper projectMapper;

    public GetDetailedCourseUseCase(CourseRepository courseRepository, StudentRepository studentRepository, AssignmentRepository assignmentRepository, ProjectRepository projectRepository, CourseMapper courseMapper, AssignmentMapper assignmentMapper, ProjectMapper projectMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
        this.courseMapper = courseMapper;
        this.assignmentMapper = assignmentMapper;
        this.projectMapper = projectMapper;
    }

    /**
     * Executes the use case to retrieve all courses.
     *
     * @return a Detailed Course
     */
    @Transactional
    public CourseDetailedResponse execute(Long id) {

        final Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        final List<Student> students = studentRepository.findByCourseId(id);
        final List<AssignmentResponse> assignments = assignmentRepository.findByCourseId(id).stream().map(assignmentMapper::domainToAssignmentResponse).toList();
        final List<ProjectResponse> projects = projectRepository.findByCourseId(id).stream().map(projectMapper::domainToProjectResponse).toList();


        return courseMapper.domainToCourseDetailedResponse(course, students, assignments, projects);
    }
}

