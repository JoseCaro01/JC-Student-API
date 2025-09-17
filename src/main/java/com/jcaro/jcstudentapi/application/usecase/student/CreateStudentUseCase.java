package com.jcaro.jcstudentapi.application.usecase.student;


import com.jcaro.jcstudentapi.application.dto.student.StudentRequest;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.StudentMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for creating a new Student.
 */
public class CreateStudentUseCase {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public CreateStudentUseCase(StudentRepository studentRepository,CourseRepository courseRepository,StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository= courseRepository;
        this.studentMapper= studentMapper;

    }

    /**
     * Creates a new Student after validating and resolving course IDs.
     *
     * @param request DTO with student data and course IDs
     * @return the created Student
     * @throws CourseNotFoundException if one or more courses do not exist
     */
    public Student execute(StudentRequest request) {
        // Resolve courses from IDs
        List<Course> courses = request.coursesId().stream()
                .map(courseId -> courseRepository.findById(courseId)
                        .orElseThrow(() -> new CourseNotFoundException(courseId)))
                .collect(Collectors.toList());

        Student student = studentMapper.requestToDomain(request,courses, Collections.emptyList(),Collections.emptyList());

        return studentRepository.save(student);
    }
}
