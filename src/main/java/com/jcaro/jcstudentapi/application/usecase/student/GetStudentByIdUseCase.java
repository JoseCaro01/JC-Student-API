package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

import java.util.Optional;

/**
 * Use case for retrieving a Student by its id.
 */
public class GetStudentByIdUseCase {

    private final StudentRepository studentRepository;

    public GetStudentByIdUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Executes the retrieval of a student by id.
     *
     * @param id the student id
     * @return the Student entity
     * @throws StudentNotFoundException if student does not exist
     */
    public Student execute(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}
