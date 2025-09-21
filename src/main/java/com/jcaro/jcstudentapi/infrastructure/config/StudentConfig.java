package com.jcaro.jcstudentapi.infrastructure.config;


import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.application.mapper.StudentMapper;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.application.service.EmailService;
import com.jcaro.jcstudentapi.application.service.ProjectPdfGeneratorService;
import com.jcaro.jcstudentapi.application.usecase.student.*;
import com.jcaro.jcstudentapi.application.usecase.user.CreateUserUseCase;
import com.jcaro.jcstudentapi.domain.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public StudentMapper studentMapper() {
        return new StudentMapper();
    }

    @Bean
    public CreateStudentUseCase createStudentUseCase(StudentRepository studentRepository,CourseRepository courseRepository,StudentMapper studentMapper) {
        return new CreateStudentUseCase(studentRepository,courseRepository,studentMapper);
    }

    @Bean
    public EditStudentUseCase editStudentUseCase(StudentRepository studentRepository,CourseRepository courseRepository,StudentMapper studentMapper) {
        return new EditStudentUseCase(studentRepository,courseRepository,studentMapper);
    }

    @Bean
    public EvaluateAssignmentUseCase evaluateAssignmentUseCase(StudentRepository studentRepository, AssignmentRepository assignmentRepository, StudentAssignmentRepository studentAssignmentRepository) {
        return new EvaluateAssignmentUseCase(studentRepository, assignmentRepository,studentAssignmentRepository);
    }

    @Bean
    public EvaluateProjectUseCase evaluateProjectUseCase(StudentRepository studentRepository, ProjectRepository projectRepository, StudentProjectRepository studentProjectRepository) {
        return new EvaluateProjectUseCase(studentRepository,projectRepository,studentProjectRepository);
    }

    @Bean
    public GetStudentByIdUseCase getStudentByIdUseCase(StudentRepository studentRepository) {
        return new GetStudentByIdUseCase(studentRepository);
    }


    @Bean
    public GetStudentsUseCase getStudentsUseCase(StudentRepository studentRepository) {
        return new GetStudentsUseCase(studentRepository);
    }

    @Bean
    public RemoveStudentUseCase removeStudentUseCase(StudentRepository studentRepository) {
        return new RemoveStudentUseCase(studentRepository);
    }

    @Bean
    public GenerateStudentScorePDFUseCase generateStudentScorePDFUseCase(StudentRepository studentRepository, AssignmentRepository assignmentRepository,StudentAssignmentRepository studentAssignmentRepository, AssignmentPdfGeneratorService assignmentPdfGeneratorService) {
        return new GenerateStudentScorePDFUseCase(studentRepository,assignmentRepository,studentAssignmentRepository,assignmentPdfGeneratorService);
    }

    @Bean
    public SendStudentScoreEmailUseCase sendStudentScoreEmailUseCase(StudentRepository studentRepository, AssignmentRepository assignmentRepository,StudentAssignmentRepository studentAssignmentRepository, EmailService emailService, AssignmentPdfGeneratorService assignmentPdfGeneratorService) {
        return new SendStudentScoreEmailUseCase(studentRepository,assignmentRepository,studentAssignmentRepository,emailService,assignmentPdfGeneratorService);
    }


    @Bean
    public GenerateStudentProjectScorePDFUseCase generateStudentProjectScorePDFUseCase(StudentRepository studentRepository,StudentProjectRepository studentProjectRepository, ProjectPdfGeneratorService projectPdfGeneratorService) {
        return new GenerateStudentProjectScorePDFUseCase(studentRepository,studentProjectRepository,projectPdfGeneratorService);
    }

    @Bean
    public SendStudentProjectScoreEmailUseCase sendStudentProjectScoreEmailUseCase(StudentRepository studentRepository, StudentProjectRepository studentProjectRepository, EmailService emailService, ProjectPdfGeneratorService projectPdfGeneratorService) {
        return new SendStudentProjectScoreEmailUseCase(studentRepository,studentProjectRepository,emailService,projectPdfGeneratorService);
    }




}
