package com.jcaro.jcstudentapi.domain.model;

/**
 * Represents a student's evaluation on a specific project.
 * Part of the Domain Layer and immutable.
 *
 * <p>This record enforces non-null constraints for essential fields and provides
 * methods to return modified copies of itself (immutability preserved).
 * Each score field represents a different evaluation criterion for the project.
 */
public record StudentProject(
        Long id,
        Student student,
        Project project,
        ScoreEnum codeQuality,
        ScoreEnum functionality,
        ScoreEnum security,
        ScoreEnum documentation,
        ScoreEnum deployment,
        ScoreEnum extra
) {

    /**
     * Constructor that validates essential fields.
     *
     * @throws IllegalArgumentException if student or project are null
     */
    public StudentProject {
        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        if (project == null) throw new IllegalArgumentException("Project cannot be null");
    }

    // --- WITH METHODS ---

    /**
     * Returns a new instance with updated id.
     *
     * @param newId the new ID
     * @return a new StudentProject instance with updated id
     */
    public StudentProject withId(Long newId) {
        return new StudentProject(newId, student, project, codeQuality, functionality, security, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated student.
     *
     * @param newStudent the new Student
     * @return a new StudentProject instance with updated student
     */
    public StudentProject withStudent(Student newStudent) {
        return new StudentProject(id, newStudent, project, codeQuality, functionality, security, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated project.
     *
     * @param newProject the new Project
     * @return a new StudentProject instance with updated project
     */
    public StudentProject withProject(Project newProject) {
        return new StudentProject(id, student, newProject, codeQuality, functionality, security, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated code quality score.
     *
     * @param newScore the new code quality score
     * @return a new StudentProject instance with updated code quality
     */
    public StudentProject withCodeQuality(ScoreEnum newScore) {
        return new StudentProject(id, student, project, newScore, functionality, security, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated functionality score.
     *
     * @param newScore the new functionality score
     * @return a new StudentProject instance with updated functionality
     */
    public StudentProject withFunctionality(ScoreEnum newScore) {
        return new StudentProject(id, student, project, codeQuality, newScore, security, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated security score.
     *
     * @param newScore the new security score
     * @return a new StudentProject instance with updated security
     */
    public StudentProject withSecurity(ScoreEnum newScore) {
        return new StudentProject(id, student, project, codeQuality, functionality, newScore, documentation, deployment, extra);
    }

    /**
     * Returns a new instance with updated documentation score.
     *
     * @param newScore the new documentation score
     * @return a new StudentProject instance with updated documentation
     */
    public StudentProject withDocumentation(ScoreEnum newScore) {
        return new StudentProject(id, student, project, codeQuality, functionality, security, newScore, deployment, extra);
    }

    /**
     * Returns a new instance with updated deployment score.
     *
     * @param newScore the new deployment score
     * @return a new StudentProject instance with updated deployment
     */
    public StudentProject withDeployment(ScoreEnum newScore) {
        return new StudentProject(id, student, project, codeQuality, functionality, security, documentation, newScore, extra);
    }

    /**
     * Returns a new instance with updated extra score.
     *
     * @param newScore the new extra score
     * @return a new StudentProject instance with updated extra
     */
    public StudentProject withExtra(ScoreEnum newScore) {
        return new StudentProject(id, student, project, codeQuality, functionality, security, documentation, deployment, newScore);
    }
}
