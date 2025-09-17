package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

/**
 * Use case for removing an existing Student.
 */
public class RemoveStudentUseCase {

    private final StudentRepository studentRepository;

    public RemoveStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Executes the removal of a student.
     *
     * @param id the student id
     * @throws StudentNotFoundException if the student does not exist
     */
    public void execute(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
