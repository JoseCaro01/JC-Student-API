package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentAssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

/**
 * Use case responsible for generating a PDF of a student's scores.
 */
@Service
public class GenerateStudentScorePDFUseCase {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final AssignmentPdfGeneratorService assignmentPdfGeneratorService;

    public GenerateStudentScorePDFUseCase(StudentRepository studentRepository,
                                         AssignmentRepository assignmentRepository,StudentAssignmentRepository studentAssignmentRepository, AssignmentPdfGeneratorService assignmentPdfGeneratorService) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentAssignmentRepository= studentAssignmentRepository;
        this.assignmentPdfGeneratorService = assignmentPdfGeneratorService;
    }

    /**
     * Generates a PDF of a student's scores.
     *
     * @param studentId the ID of the student
     * @param courseId  optional course ID to filter scores
     * @return ByteArrayInputStream of the generated PDF
     */
    public ByteArrayInputStream execute(Long studentId, Long courseId) {
        // Retrieve the student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        final Long targetCourseId;
        if (courseId != null) {
            final boolean courseExists = student.courses().stream()
                    .anyMatch(c -> c.id().equals(courseId));
            if (!courseExists) {
                throw new CourseNotFoundException(courseId);
            }
            targetCourseId = courseId;
        } else {
            if (student.courses().isEmpty()) {
                throw new CourseNotFoundException(null);
            }
            targetCourseId = student.courses().get(student.courses().size() - 1).id();
        }

        final List<Assignment> assignmentList = assignmentRepository.findByCourseId(targetCourseId);
        final List<StudentAssignment> studentAssignmentList = studentAssignmentRepository.findByStudentIdAndCourseId(studentId,targetCourseId);

        try {
            return assignmentPdfGeneratorService.generate(student,assignmentList,studentAssignmentList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong generating the PDF");
        }
    }
}
