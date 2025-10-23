package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.dto.studentAssignment.StudentAssignmentRequest;
import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.StudentAssignmentMapper;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentAssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.AssignmentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.mapper.StudentAssignmentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.mapper.StudentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentAssignmentEntity;

import java.util.List;

/**
 * Use case for evaluating an assignment for a student.
 */
public class EvaluateAssignmentUseCase {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final StudentAssignmentMapper studentAssignmentMapper;


    public EvaluateAssignmentUseCase(StudentRepository studentRepository,
                                     AssignmentRepository assignmentRepository,
                                     StudentAssignmentRepository studentAssignmentRepository,StudentAssignmentMapper studentAssignmentMapper) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.studentAssignmentMapper= studentAssignmentMapper;
    }

    /**
     * Executes the evaluation of a student for an assignment.
     *
     * @param studentId   the student id
     * @param studentAssignmentRequests the studentAssignment list for evaluate the assignments
     *
     * @throws StudentNotFoundException if student don't exist
     * @throws AssignmentNotFoundException if assignment don't exist
     */
    public void execute(Long studentId, List<StudentAssignmentRequest> studentAssignmentRequests) {

       final  Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

       final List<StudentAssignment> studentAssignments =  studentAssignmentRequests.stream().map(studentAssignmentRequest -> {


           final   Assignment assignment = assignmentRepository.findById(studentAssignmentRequest.assignmentId())
                   .orElseThrow(() -> new AssignmentNotFoundException(studentAssignmentRequest.assignmentId()));

           return  studentAssignmentRepository
                   .findByStudentIdAndAssignmentId(studentId, studentAssignmentRequest.assignmentId())
                   .orElse(studentAssignmentMapper.requestToDomain(student,assignment,studentAssignmentRequest)).withScore(ScoreEnum.fromValue(studentAssignmentRequest.score()));


       }).toList();



        studentAssignmentRepository.saveAll(studentAssignments);
    }
}
