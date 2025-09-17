package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.Assignment;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Assignment.
 *
 * Defines the operations required by the domain to manage assignments,
 * without specifying how the data is persisted.
 * This is a pure Domain Layer interface (port).
 */
public interface AssignmentRepository {

    /**
     * Saves an assignment.
     *
     * @param assignment the Assignment to save
     * @return the saved Assignment
     */
    Assignment save(Assignment assignment);

    /**
     * Finds an assignment by its ID.
     *
     * @param id the ID of the assignment
     * @return an Optional containing the Assignment if found, empty otherwise
     */
    Optional<Assignment> findById(Long id);

    /**
     * Retrieves all assignments.
     *
     * @return a list of all assignments
     */
    List<Assignment> findAll();

    /**
     * Retrieves all assignments for a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of assignments belonging to the specified course
     */
    List<Assignment> findByCourseId(Long courseId);

    /**
     * Deletes an assignment by its ID.
     *
     * @param id the ID of the assignment to delete
     */
    void deleteById(Long id);
}
