package com.jcaro.jcstudentapi.application.usecase.project;

import com.jcaro.jcstudentapi.domain.model.Project;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
/**
 * Use case for getting Projects.
 */
public class GetAllProjectsUseCase {

    private final ProjectRepository projectRepository;

    public GetAllProjectsUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Returns all projects or filters them by courseId if present.
     *
     * @param courseId optional course id
     * @return list of projects
     */
    public List<Project> execute(Long courseId) {
        return courseId!=null
                ? projectRepository.findByCourseId(courseId)
                : projectRepository.findAll();
    }
}
