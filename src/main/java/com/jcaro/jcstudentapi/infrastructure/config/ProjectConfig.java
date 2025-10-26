package com.jcaro.jcstudentapi.infrastructure.config;


import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.application.usecase.project.*;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ProjectMapper projectMapper() {
        return new ProjectMapper();
    }

    @Bean
    public CreateProjectUseCase createProjectUseCase(ProjectRepository projectRepository, CourseRepository courseRepository, ProjectMapper projectMapper) {
        return new CreateProjectUseCase(projectRepository, courseRepository, projectMapper);
    }

    @Bean
    public EditProjectUseCase editProjectUseCase(ProjectRepository projectRepository, CourseRepository courseRepository, ProjectMapper projectMapper) {
        return new EditProjectUseCase(projectRepository, courseRepository, projectMapper);
    }

    @Bean
    public GetAllProjectsUseCase getAllProjectsUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        return new GetAllProjectsUseCase(projectRepository, projectMapper);
    }

    @Bean
    public GetProjectByIdUseCase getProjectByIdUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        return new GetProjectByIdUseCase(projectRepository, projectMapper);
    }

    @Bean
    public RemoveProjectUseCase removeProjectUseCase(ProjectRepository projectRepository) {
        return new RemoveProjectUseCase(projectRepository);
    }
}
