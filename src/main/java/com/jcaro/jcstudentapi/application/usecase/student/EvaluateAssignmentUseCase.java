package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentAssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;

/**
 * Use case for evaluating an assignment for a student.
 */
public class EvaluateAssignmentUseCase {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;

    public EvaluateAssignmentUseCase(StudentRepository studentRepository,
                                     AssignmentRepository assignmentRepository,
                                     StudentAssignmentRepository studentAssignmentRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
    }

    /**
     * Executes the evaluation of a student for an assignment.
     *
     * @param studentId   the student id
     * @param assignmentId the assignment id
     * @param scoreEnum   the score to assign
     *
     * @throws StudentNotFoundException if student don't exist
     * @throws AssignmentNotFoundException if assignment don't exist
     */
    public void execute(Long studentId, Long assignmentId, ScoreEnum scoreEnum) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));

        StudentAssignment studentAssignment = studentAssignmentRepository
                .findByStudentIdAndAssignmentId(studentId, assignmentId)
                .orElse(new StudentAssignment(null,student, assignment, scoreEnum));



        studentAssignmentRepository.save(studentAssignment.withScore(scoreEnum));
    }
}
