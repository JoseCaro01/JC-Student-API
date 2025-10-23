package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.course.CourseDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.dto.student.StudentDetailedResponse;
import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentResponse;
import com.jcaro.jcstudentapi.application.dto.studentProject.StudentProjectResponse;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.*;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Use case to retrieve all courses.
 * <p>
 * This class encapsulates the business logic for getting all courses
 * from the repository. It does not perform any validation or
 * transformation; it simply delegates to the CourseRepository.
 */
public class GetDetailedStudentUseCase {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final ProjectRepository projectRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final StudentProjectRepository studentProjectRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final AssignmentMapper assignmentMapper;
    private final ProjectMapper projectMapper;
    private final StudentAssignmentMapper studentAssignmentMapper;
    private final StudentProjectMapper studentProjectMapper;

    public GetDetailedStudentUseCase(CourseRepository courseRepository, StudentRepository studentRepository, AssignmentRepository assignmentRepository, ProjectRepository projectRepository, CourseMapper courseMapper, StudentMapper studentMapper, AssignmentMapper assignmentMapper, ProjectMapper projectMapper, StudentAssignmentRepository studentAssignmentRepository, StudentProjectRepository studentProjectRepository, StudentAssignmentMapper studentAssignmentMapper, StudentProjectMapper studentProjectMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.studentProjectRepository = studentProjectRepository;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
        this.assignmentMapper = assignmentMapper;
        this.projectMapper = projectMapper;
        this.studentAssignmentMapper = studentAssignmentMapper;
        this.studentProjectMapper = studentProjectMapper;
    }

    /**
     * Executes the use case to retrieve all courses.
     *
     * @return a Detailed Course
     */
    @Transactional
    public StudentDetailedResponse execute(Long id, Long courseId) {
        final Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        final Course course = student.courses().stream().filter(courseSelected -> courseSelected.id().equals(courseId)).findFirst().orElseThrow(() -> new CourseNotFoundException(courseId));
        final List<AssignmentResponse> assignments = assignmentRepository.findByCourseId(course.id()).stream().map(assignmentMapper::domainToAssignmentResponse).toList();
        final List<ProjectResponse> projects = projectRepository.findByCourseId(course.id()).stream().map(projectMapper::domainToProjectResponse).toList();
        final CourseDetailedResponse courseDetailedResponses = courseMapper.domainToCourseDetailedResponse(course, Collections.emptyList(), assignments, projects);

        final List<StudentAssignmentResponse> studentAssignmentResponses = studentAssignmentRepository.findByStudentIdAndCourseId(id,courseId).stream().map(studentAssignmentMapper::domainToStudentAssignmentResponse).toList();
        final List<StudentProjectResponse> studentProjectResponses = studentProjectRepository.findByStudentIdAndCourseId(id,courseId).stream().map(studentProjectMapper::domainToStudentProjectResponse).toList();

        return studentMapper.domainToStudentDetailedResponse(student,courseDetailedResponses,studentAssignmentResponses,studentProjectResponses );
    }
}

