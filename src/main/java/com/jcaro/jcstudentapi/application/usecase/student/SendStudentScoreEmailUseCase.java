package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.application.service.EmailService;
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
 * Use case responsible for sending a student's score PDF via email.
 */
@Service
public class SendStudentScoreEmailUseCase {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final EmailService emailService;
    private final AssignmentPdfGeneratorService assignmentPdfGeneratorService;

    public SendStudentScoreEmailUseCase(StudentRepository studentRepository,

                                        AssignmentRepository assignmentRepository,
                                        StudentAssignmentRepository studentAssignmentRepository,
                                        EmailService emailService,AssignmentPdfGeneratorService assignmentPdfGeneratorService) {
        this.studentRepository = studentRepository;
        this.assignmentRepository= assignmentRepository;
        this.studentAssignmentRepository=studentAssignmentRepository;
        this.emailService = emailService;
        this.assignmentPdfGeneratorService= assignmentPdfGeneratorService;
    }

    /**
     * Sends a student's score PDF to their email.
     *
     * @param studentId the student id
     * @param courseId  optional course id (if null, generate full score)
     */
    public void execute(Long studentId, Long courseId) {
       final  Student student = studentRepository.findById(studentId)
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
          final  ByteArrayInputStream pdf = assignmentPdfGeneratorService.generate(student, assignmentList,studentAssignmentList);
            emailService.sendEmailWithPdfScore(student, pdf);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot generate score PDF for this student/course combination");
        }
    }
}
