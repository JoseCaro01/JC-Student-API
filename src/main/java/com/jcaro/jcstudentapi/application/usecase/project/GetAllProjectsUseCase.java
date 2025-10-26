package com.jcaro.jcstudentapi.application.usecase.project;

import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
/**
 * Use case for getting Projects.
 */
public class GetAllProjectsUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    public GetAllProjectsUseCase(ProjectRepository projectRepository,ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper= projectMapper;
    }

    /**
     * Returns all projects or filters them by courseId if present.
     *
     * @param courseId optional course id
     * @return list of projects
     */
    public List<ProjectResponse> execute(Long courseId) {
        final List<Project> projects = courseId!=null
                ? projectRepository.findByCourseId(courseId)
                : projectRepository.findAll();

        return projects.stream().map(projectMapper::domainToProjectResponse).toList();
    }
}
