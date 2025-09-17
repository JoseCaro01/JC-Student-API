package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.StudentProject;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for StudentProject.
 *
 * Defines the operations required by the domain to manage student projects,
 * without specifying how the data is persisted.
 * This is a pure Domain Layer interface (port).
 */
public interface StudentProjectRepository {

    /**
     * Saves a StudentProject.
     *
     * @param studentProject the StudentProject to save
     * @return the saved StudentProject
     */
    StudentProject save(StudentProject studentProject);

    /**
     * Finds a StudentProject by its ID.
     *
     * @param id the ID of the StudentProject
     * @return an Optional containing the StudentProject if found, empty otherwise
     */
    Optional<StudentProject> findById(Long id);

    /**
     * Retrieves all StudentProjects.
     *
     * @return a list of all StudentProjects
     */
    List<StudentProject> findAll();

    /**
     * Finds a StudentProject by student ID and project ID.
     *
     * @param studentId the ID of the student
     * @param projectId the ID of the project
     * @return an Optional containing the StudentProject if found, empty otherwise
     */
    Optional<StudentProject> findByStudentIdAndProjectId(Long studentId, Long projectId);

    /**
     * Deletes a StudentProject by its ID.
     *
     * @param id the ID of the StudentProject to delete
     */
    void deleteById(Long id);
}
