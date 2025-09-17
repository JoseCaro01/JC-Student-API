package com.jcaro.jcstudentapi.infrastructure.config;


import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.application.usecase.project.CreateProjectUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.EditProjectUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.GetAllProjectsUseCase;
import com.jcaro.jcstudentapi.application.usecase.project.RemoveProjectUseCase;
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
    public CreateProjectUseCase createProjectUseCase(ProjectRepository projectRepository,CourseRepository courseRepository,ProjectMapper projectMapper) {
        return new CreateProjectUseCase(projectRepository,courseRepository, projectMapper);
    }

    @Bean
    public EditProjectUseCase editProjectUseCase(ProjectRepository projectRepository, CourseRepository courseRepository,ProjectMapper projectMapper) {
        return new EditProjectUseCase(projectRepository,courseRepository,projectMapper);
    }

    @Bean
    public GetAllProjectsUseCase getAllProjectsUseCase(ProjectRepository projectRepository) {
        return new GetAllProjectsUseCase(projectRepository);
    }

    @Bean
    public RemoveProjectUseCase removeProjectUseCase(ProjectRepository projectRepository) {
        return new RemoveProjectUseCase(projectRepository);
    }
}
