package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.Student;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student.
 *
 * Defines the operations required by the domain to manage students,
 * without specifying how the data is persisted.
 * This is a pure Domain Layer interface (port).
 */
public interface StudentRepository {

    /**
     * Saves a Student.
     *
     * @param student the Student to save
     * @return the saved Student
     */
    Student save(Student student);

    /**
     * Finds a Student by its ID.
     *
     * @param id the ID of the student
     * @return an Optional containing the Student if found, empty otherwise
     */
    Optional<Student> findById(Long id);

    /**
     * Retrieves all Students.
     *
     * @return a list of all students
     */
    List<Student> findAll();

    /**
     * Retrieves all students enrolled in a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of students enrolled in the course
     */
    List<Student> findByCourseId(Long courseId);

    /**
     * Retrieves all students that have a specific assignment.
     *
     * @param assignmentId the ID of the assignment
     * @return a list of students that have the assignment
     */
    List<Student> findByAssignmentId(Long assignmentId);

    /**
     * Retrieves all students that have a specific project.
     *
     * @param projectId the ID of the project
     * @return a list of students that have the project
     */
    List<Student> findByProjectId(Long projectId);

    /**
     * Deletes a student by its ID.
     *
     * @param id the ID of the student to delete
     */
    void deleteById(Long id);
}
