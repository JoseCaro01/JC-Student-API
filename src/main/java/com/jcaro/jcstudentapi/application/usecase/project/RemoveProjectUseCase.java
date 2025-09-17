package com.jcaro.jcstudentapi.application.usecase.project;

import com.jcaro.jcstudentapi.application.exception.project.ProjectNotFoundException;
import com.jcaro.jcstudentapi.domain.repository.ProjectRepository;

/**
 * Use case for remove a Project.
 */
public class RemoveProjectUseCase {

    private final ProjectRepository projectRepository;

    public RemoveProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Removes a project by id after verifying it exists.
     *
     * @param id project id
     * @throws ProjectNotFoundException if the project does not exist
     */
    public void execute(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
    }
}
