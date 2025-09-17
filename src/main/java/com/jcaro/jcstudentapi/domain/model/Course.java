package com.jcaro.jcstudentapi.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a course in the academic system.
 * Part of the Domain Layer and immutable.
 */
public record Course(
        Long id,
        String name,
        String programmingLanguage,
        List<Student> students,
        List<Assignment> assignments,
        List<Project> projects
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

        if (students == null) students = Collections.emptyList();
        if (assignments == null) assignments = Collections.emptyList();
        if (projects == null) projects = Collections.emptyList();
    }

    /**
     * Returns a copy of this Course with a new id.
     *
     * @param id the new id
     * @return a new Course instance
     */
    public Course withId(Long id) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }

    /**
     * Returns a copy of this Course with a new name.
     *
     * @param name the new name
     * @return a new Course instance
     */
    public Course withName(String name) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }

    /**
     * Returns a copy of this Course with a new programming language.
     *
     * @param programmingLanguage the new programming language
     * @return a new Course instance
     */
    public Course withProgrammingLanguage(String programmingLanguage) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }

    /**
     * Returns a copy of this Course with a new list of students.
     *
     * @param students the new students list
     * @return a new Course instance
     */
    public Course withStudents(List<Student> students) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }

    /**
     * Returns a copy of this Course with a new list of assignments.
     *
     * @param assignments the new assignments list
     * @return a new Course instance
     */
    public Course withAssignments(List<Assignment> assignments) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }

    /**
     * Returns a copy of this Course with a new list of projects.
     *
     * @param projects the new projects list
     * @return a new Course instance
     */
    public Course withProjects(List<Project> projects) {
        return new Course(id, name, programmingLanguage, students, assignments, projects);
    }
}
