package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Use case for retrieving students with optional filters.
 */
public class GetStudentsUseCase {

    private final StudentRepository studentRepository;

    public GetStudentsUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Executes the retrieval of students.
     *
     * @param courseId optional filter by course
     * @return list of students
     */
    public List<Student> execute(Optional<Long> courseId) {
        if (courseId.isPresent()) {
            return studentRepository.findByCourseId(courseId.get());
        }
        return studentRepository.findAll();
    }
}
