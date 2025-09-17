package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for StudentAssignment.
 *
 * Defines the operations required by the domain to manage student assignments,
 * without specifying how the data is persisted.
 * This is a pure Domain Layer interface (port).
 */
public interface StudentAssignmentRepository {

    /**
     * Saves a StudentAssignment.
     *
     * @param studentAssignment the StudentAssignment to save
     * @return the saved StudentAssignment
     */
    StudentAssignment save(StudentAssignment studentAssignment);

    /**
     * Finds a StudentAssignment by its ID.
     *
     * @param id the ID of the StudentAssignment
     * @return an Optional containing the StudentAssignment if found, empty otherwise
     */
    Optional<StudentAssignment> findById(Long id);

    /**
     * Retrieves all StudentAssignments.
     *
     * @return a list of all StudentAssignments
     */
    List<StudentAssignment> findAll();

    /**
     * Finds a StudentAssignment by student ID and assignment ID.
     *
     * @param studentId the ID of the student
     * @param assignmentId the ID of the assignment
     * @return an Optional containing the StudentAssignment if found, empty otherwise
     */
    Optional<StudentAssignment> findByStudentIdAndAssignmentId(Long studentId, Long assignmentId);

    /**
     * Deletes a StudentAssignment by its ID.
     *
     * @param id the ID of the StudentAssignment to delete
     */
    void deleteById(Long id);
}
