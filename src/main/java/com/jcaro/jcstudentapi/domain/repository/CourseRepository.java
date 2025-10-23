package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.Course;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Course.
 *
 * Defines the operations the domain requires for managing courses,
 * without specifying how the data is stored or retrieved.
 * This is a pure Domain Layer interface (port).
 */
public interface CourseRepository {

    /**
     * Saves a course.
     *
     * @param course the Course to save
     * @return the saved Course
     */
    Course save(Course course);

    /**
     * Finds a course by its ID.
     *
     * @param id the ID of the course
     * @return an Optional containing the Course if found, empty otherwise
     */
    Optional<Course> findById(Long id);

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    List<Course> findAll();


    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     */
    void deleteById(Long id);
}
