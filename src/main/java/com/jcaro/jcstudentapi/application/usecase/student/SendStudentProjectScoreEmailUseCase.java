package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.application.service.EmailService;
import com.jcaro.jcstudentapi.application.service.ProjectPdfGeneratorService;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentAssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentProjectRepository;
import com.jcaro.jcstudentapi.domain.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Use case responsible for sending a student's project score PDF via email.
 */
@Service
public class SendStudentProjectScoreEmailUseCase {


    private final StudentRepository studentRepository;
    private final StudentProjectRepository studentProjectRepository;
    private final EmailService emailService;
    private final ProjectPdfGeneratorService projectPdfGeneratorService;

    public SendStudentProjectScoreEmailUseCase(StudentRepository studentRepository,
                                               StudentProjectRepository studentProjectRepository,
                                               EmailService emailService, ProjectPdfGeneratorService projectPdfGeneratorService) {
        this.studentRepository = studentRepository;
        this.studentProjectRepository = studentProjectRepository;
        this.emailService = emailService;
        this.projectPdfGeneratorService = projectPdfGeneratorService;
    }

    /**
     * Sends a student's score PDF to their email.
     *
     * @param studentId the student id
     * @param courseId  optional course id (if null, generate full score)
     */
    public void execute(Long studentId, Long courseId) {
        final Student student = studentRepository.findById(studentId)
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

        final List<StudentProject> studentProjects = studentProjectRepository.findByStudentIdAndCourseId(studentId, targetCourseId);


        try {
            final ByteArrayInputStream pdf = projectPdfGeneratorService.generate(student, studentProjects);
            emailService.sendEmailWithPdfScore(student, pdf);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot generate score PDF for this student/course combination");
        }
    }
}
