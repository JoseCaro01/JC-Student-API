package com.jcaro.jcstudentapi.application.usecase.project;

import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.exception.course.CourseNotFoundException;
import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

/**
 * Use case for creating a new Project.
 */
public class CreateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final CourseRepository courseRepository;
    private final ProjectMapper projectMapper;

    public CreateProjectUseCase(ProjectRepository projectRepository, CourseRepository courseRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.courseRepository = courseRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Creates a new Project after validating the associated Course.
     *
     * @param project the project to create
     * @return the created project
     * @throws CourseNotFoundException if the course does not exist
     */
    public ProjectResponse execute(ProjectRequest project) {
        if (project.courseId() == null) {
            throw new CourseNotFoundException(-1L);
        }

        final Course course = courseRepository.findById(project.courseId()).orElseThrow(() -> new CourseNotFoundException(project.courseId()));
        return projectMapper.domainToProjectResponse(projectRepository.save(projectMapper.requestToDomain(project, course)));
    }
}
