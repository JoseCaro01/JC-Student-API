package com.jcaro.jcstudentapi.infrastructure.config;

import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.application.usecase.assignment.*;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssignmentConfig {

    @Bean
    public AssignmentMapper assignmentMapper() {
        return new AssignmentMapper();
    }

    @Bean
    public CreateAssignmentUseCase createAssignmentUseCase(AssignmentRepository assignmentRepository, CourseRepository courseRepository,AssignmentMapper assignmentMapper) {
        return new CreateAssignmentUseCase(assignmentRepository,courseRepository,assignmentMapper);
    }

    @Bean
    public EditAssignmentUseCase editAssignmentUseCase(AssignmentRepository assignmentRepository,CourseRepository courseRepository,AssignmentMapper assignmentMapper) {
        return new EditAssignmentUseCase(assignmentRepository,courseRepository,assignmentMapper);
    }

    @Bean
    public GetAllAssignmentsUseCase getAllAssignmentsUseCase(AssignmentRepository assignmentRepository,AssignmentMapper assignmentMapper) {
        return new GetAllAssignmentsUseCase(assignmentRepository,assignmentMapper);
    }

    @Bean
    public GetAssignmentByIdUseCase getAssignmentByIdUseCase(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        return new GetAssignmentByIdUseCase(assignmentRepository,assignmentMapper);
    }


    @Bean
    public RemoveAssignmentUseCase removeAssignmentUseCase(AssignmentRepository assignmentRepository) {
        return new RemoveAssignmentUseCase(assignmentRepository);
    }
}