package com.jcaro.jcstudentapi.domain.model;


/**
 * Represents a course in the academic system.
 * Part of the Domain Layer and immutable.
 */
public record Course(
        Long id,
        String name,
        String programmingLanguage
) {

    /**
     * Constructor that validates essential fields and initializes collections if null.
     *
     * @throws IllegalArgumentException if name or programmingLanguage are null or blank
     */
    public Course {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Course name cannot be empty");
        if (programmingLanguage == null || programmingLanguage.isBlank())
            throw new IllegalArgumentException("Programming language cannot be empty");

    }

    /**
     * Returns a copy of this Course with a new id.
     *
     * @param id the new id
     * @return a new Course instance
     */
    public Course withId(Long id) {
        return new Course(id, name, programmingLanguage);
    }

    /**
     * Returns a copy of this Course with a new name.
     *
     * @param name the new name
     * @return a new Course instance
     */
    public Course withName(String name) {
        return new Course(id, name, programmingLanguage);
    }

    /**
     * Returns a copy of this Course with a new programming language.
     *
     * @param programmingLanguage the new programming language
     * @return a new Course instance
     */
    public Course withProgrammingLanguage(String programmingLanguage) {
        return new Course(id, name, programmingLanguage);
    }

}
