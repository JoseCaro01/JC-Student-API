package com.jcaro.jcstudentapi.domain.model;

/**
 * Represents a student's score for a specific assignment.
 * Part of the Domain Layer and immutable.
 */
public record StudentAssignment(
        Long id,
        Student student,
        Assignment assignment,
        ScoreEnum score
) {

    /**
     * Constructor that validates essential fields.
     *
     * @throws IllegalArgumentException if student, assignment, or score are null
     */
    public StudentAssignment {
        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        if (assignment == null) throw new IllegalArgumentException("Assignment cannot be null");
        if (score == null) throw new IllegalArgumentException("Score cannot be null");
    }

    /**
     * Returns a new StudentAssignment instance with updated score.
     *
     * @param newScore the new score
     * @return a new StudentAssignment instance with the updated score
     */
    public StudentAssignment withScore(ScoreEnum newScore) {
        return new StudentAssignment(id, student, assignment, newScore);
    }
}
