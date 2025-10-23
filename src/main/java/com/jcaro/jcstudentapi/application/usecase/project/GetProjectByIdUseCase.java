package com.jcaro.jcstudentapi.application.usecase.project;


import com.jcaro.jcstudentapi.application.dto.project.ProjectRequest;
import com.jcaro.jcstudentapi.application.dto.project.ProjectResponse;
import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.ProjectMapper;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

/**
 * Use case for retrieving a Course by its id.
 */
public class GetProjectByIdUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public GetProjectByIdUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Executes the retrieval of a project by id.
     *
     * @param id the project id
     * @return the project entity
     * @throws ProjectNotFoundException if project does not exist
     */
    public ProjectResponse execute(Long id) {
        return projectMapper.domainToProjectResponse(projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id)));
    }
}
