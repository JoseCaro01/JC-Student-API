package com.jcaro.jcstudentapi.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a student in the academic system.
 * Part of the Domain Layer and immutable.
 */
public record Student(
        Long id,
        String name,
        String email,
        List<Course> courses,
        List<StudentAssignment> assignments,
        List<StudentProject> projects
) {

    /**
     * Constructor that validates essential fields and initializes collections if null.
     *
     * @throws IllegalArgumentException if name or email are null or blank
     */
    public Student {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Student name cannot be empty");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Student email cannot be empty");

        if (courses == null) courses = Collections.emptyList();
        if (assignments == null) assignments = Collections.emptyList();
        if (projects == null) projects = Collections.emptyList();
    }

    // ======= With methods =======

    /**
     * Returns a copy of this Student with a new id.
     *
     * @param id the new student id
     * @return a new Student instance
     */
    public Student withId(Long id) {
        return new Student(id, name, email, courses, assignments, projects);
    }


    /**
     * Returns a copy of this Student with a new name.
     *
     * @param name the new student name
     * @return a new Student instance
     */
    public Student withName(String name) {
        return new Student(id, name, email, courses, assignments, projects);
    }

    /**
     * Returns a copy of this Student with a new email.
     *
     * @param email the new email
     * @return a new Student instance
     */
    public Student withEmail(String email) {
        return new Student(id, name, email, courses, assignments, projects);
    }

    /**
     * Returns a copy of this Student with a new list of courses.
     *
     * @param courses the new courses list
     * @return a new Student instance
     */
    public Student withCourses(List<Course> courses) {
        return new Student(id, name, email, courses, assignments, projects);
    }

    /**
     * Returns a copy of this Student with a new list of assignments.
     *
     * @param assignments the new assignments list
     * @return a new Student instance
     */
    public Student withAssignments(List<StudentAssignment> assignments) {
        return new Student(id, name, email, courses, assignments, projects);
    }

    /**
     * Returns a copy of this Student with a new list of projects.
     *
     * @param projects the new projects list
     * @return a new Student instance
     */
    public Student withProjects(List<StudentProject> projects) {
        return new Student(id, name, email, courses, assignments, projects);
    }
}
