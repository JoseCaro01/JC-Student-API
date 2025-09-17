package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.Project;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Project.
 *
 * Defines the operations required by the domain to manage projects,
 * without specifying how the data is persisted.
 * This is a pure Domain Layer interface (port).
 */
public interface ProjectRepository {

    /**
     * Saves a project.
     *
     * @param project the Project to save
     * @return the saved Project
     */
    Project save(Project project);

    /**
     * Finds a project by its ID.
     *
     * @param id the ID of the project
     * @return an Optional containing the Project if found, empty otherwise
     */
    Optional<Project> findById(Long id);

    /**
     * Retrieves all projects.
     *
     * @return a list of all projects
     */
    List<Project> findAll();

    /**
     * Retrieves all projects for a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of projects belonging to the specified course
     */
    List<Project> findByCourseId(Long courseId);

    /**
     * Deletes a project by its ID.
     *
     * @param id the ID of the project to delete
     */
    void deleteById(Long id);
}
