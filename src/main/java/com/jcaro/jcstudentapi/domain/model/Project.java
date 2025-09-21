package com.jcaro.jcstudentapi.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an academic project within a course.
 * Part of the Domain Layer and immutable.
 */
public record Project(
        Long id,
        String name,
        String description,
        Course course
) {

    /**
     * Constructor that validates essential fields and initializes collections if null.
     *
     * @throws IllegalArgumentException if name or course are null or blank
     */
    public Project {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Project name cannot be empty");
        if (course == null) throw new IllegalArgumentException("Course cannot be null");
    }

    // ======= With methods =======


    /**
     * Returns a copy of this Project with a new id.
     *
     * @param id the new project id
     * @return a new Project instance
     */
    public Project withId(Long id) {
        return new Project(id, name, description, course);
    }


    /**
     * Returns a copy of this Project with a new name.
     *
     * @param name the new project name
     * @return a new Project instance
     */
    public Project withName(String name) {
        return new Project(id, name, description, course);
    }

    /**
     * Returns a copy of this Project with a new description.
     *
     * @param description the new description
     * @return a new Project instance
     */
    public Project withDescription(String description) {
        return new Project(id, name, description, course);
    }

    /**
     * Returns a copy of this Project with a new course.
     *
     * @param course the new course
     * @return a new Project instance
     */
    public Project withCourse(Course course) {
        return new Project(id, name, description, course);
    }

}
