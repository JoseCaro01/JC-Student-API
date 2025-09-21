package com.jcaro.jcstudentapi.application.usecase.student;

import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.student.StudentNotFoundException;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.application.service.ProjectPdfGeneratorService;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.domain.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Use case responsible for generating a PDF of a student's scores.
 */
@Service
public class GenerateStudentProjectScorePDFUseCase {

    private final StudentRepository studentRepository;
    private final StudentProjectRepository studentProjectRepository;
    private final ProjectPdfGeneratorService projectPdfGeneratorService;

    public GenerateStudentProjectScorePDFUseCase(StudentRepository studentRepository,
                                                 StudentProjectRepository studentProjectRepository, ProjectPdfGeneratorService projectPdfGeneratorService) {
        this.studentRepository = studentRepository;
        this.studentProjectRepository= studentProjectRepository;
        this.projectPdfGeneratorService = projectPdfGeneratorService;
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

        final List<StudentProject> studentProjects  = studentProjectRepository.findByStudentIdAndCourseId(studentId,targetCourseId);

        try {
            return projectPdfGeneratorService.generate(student,studentProjects);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong generating the PDF");
        }
    }
}
