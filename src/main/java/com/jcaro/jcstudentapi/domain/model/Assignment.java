package com.jcaro.jcstudentapi.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an academic assignment within a course.
 * Part of the Domain Layer and immutable.
 */
public record Assignment(
        Long id,
        String name,
        String description,
        boolean obligatory,
        Course course
) {

    /**
     * Validates that the assignment's name and course are not null.
     * Ensures the studentAssignments list is initialized if null.
     *
     * @throws IllegalArgumentException if name or course are null or blank
     */
    public Assignment {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Assignment name cannot be empty");
        if (course == null)
            throw new IllegalArgumentException("Course cannot be null");

    }


    /**
     * Returns a copy of this Assignment with a new id.
     *
     * @param id the new id
     * @return a new Assignment instance
     */
    public Assignment withId(Long id) {
        return new Assignment(id, name, description, obligatory, course);
    }

    /**
     * Returns a copy of this Assignment with a new name.
     *
     * @param name the new name
     * @return a new Assignment instance
     */
    public Assignment withName(String name) {
        return new Assignment(id, name, description, obligatory, course);
    }

    /**
     * Returns a copy of this Assignment with a new description.
     *
     * @param description the new description
     * @return a new Assignment instance
     */
    public Assignment withDescription(String description) {
        return new Assignment(id, name, description, obligatory, course);
    }

    /**
     * Returns a copy of this Assignment with a new obligatory flag.
     *
     * @param obligatory the new obligatory value
     * @return a new Assignment instance
     */
    public Assignment withObligatory(boolean obligatory) {
        return new Assignment(id, name, description, obligatory, course);
    }

    /**
     * Returns a copy of this Assignment with a new course.
     *
     * @param course the new course
     * @return a new Assignment instance
     */
    public Assignment withCourse(Course course) {
        return new Assignment(id, name, description, obligatory, course);
    }

}
