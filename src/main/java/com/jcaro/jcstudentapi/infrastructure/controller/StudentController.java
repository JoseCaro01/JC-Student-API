package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.student.StudentEvaluateProjectRequest;
import com.jcaro.jcstudentapi.application.dto.student.StudentRequest;
import com.jcaro.jcstudentapi.application.usecase.student.*;
import com.jcaro.jcstudentapi.domain.model.ScoreEnum;
import com.jcaro.jcstudentapi.domain.model.Student;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing students.
 * <p>
 * This controller belongs to the Infrastructure layer and acts as the inbound adapter,
 * exposing HTTP endpoints for student-related operations.
 * It delegates all business logic to the corresponding use cases in the Application layer.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;
    private final EditStudentUseCase editStudentUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private final GetStudentsUseCase getStudentsUseCase;
    private final RemoveStudentUseCase removeStudentUseCase;
    private final EvaluateAssignmentUseCase evaluateAssignmentUseCase;
    private final EvaluateProjectUseCase evaluateProjectUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase,
                             EditStudentUseCase editStudentUseCase,
                             GetStudentByIdUseCase getStudentByIdUseCase,
                             GetStudentsUseCase getStudentsUseCase,
                             RemoveStudentUseCase removeStudentUseCase,
                             EvaluateAssignmentUseCase evaluateAssignmentUseCase,
                             EvaluateProjectUseCase evaluateProjectUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.editStudentUseCase = editStudentUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
        this.getStudentsUseCase = getStudentsUseCase;
        this.removeStudentUseCase = removeStudentUseCase;
        this.evaluateAssignmentUseCase = evaluateAssignmentUseCase;
        this.evaluateProjectUseCase = evaluateProjectUseCase;
    }

    // --- CRUD Endpoints ---

    /**
     * Creates a new student.
     *
     * @param student the request payload containing student details
     * @return the created {@link Student}
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequest student) {
        return new ResponseEntity<>(createStudentUseCase.execute(student), HttpStatus.OK);
    }

    /**
     * Updates an existing student by ID.
     *
     * @param id      the ID of the student to update
     * @param student the request payload containing updated student details
     * @return the updated {@link Student}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody @Valid StudentRequest student) {
        return new ResponseEntity<>(editStudentUseCase.execute(id, student), HttpStatus.OK);
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id the ID of the student to retrieve
     * @return the found {@link Student}, if exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(getStudentByIdUseCase.execute(id), HttpStatus.OK);
    }

    /**
     * Retrieves all students, optionally filtered by course ID.
     *
     * @param courseId optional ID of the course to filter students
     * @return a list of {@link Student}
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@Param("courseId") Optional<Long> courseId) {
        return new ResponseEntity<>(getStudentsUseCase.execute(courseId), HttpStatus.OK);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id the ID of the student to delete
     * @return {@link HttpStatus#NO_CONTENT} if deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        removeStudentUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // --- Evaluation Endpoints ---

    /**
     * Evaluates a student's assignment by assigning a score.
     *
     * @param studentId   the ID of the student being evaluated
     * @param assignmentId the ID of the assignment
     * @param score        the score assigned to the student for the assignment
     * @return {@link HttpStatus#OK} if evaluation was successful
     */
    @PostMapping("/{studentId}/assignments/{assignmentId}/evaluate")
    public ResponseEntity<Void> evaluateAssignment(
            @PathVariable Long studentId,
            @PathVariable Long assignmentId,
            @RequestParam ScoreEnum score
    ) {
        evaluateAssignmentUseCase.execute(studentId, assignmentId, score);
        return ResponseEntity.ok().build();
    }

    /**
     * Evaluates a student's project using multiple scoring categories.
     *
     * @param studentId                    the ID of the student being evaluated
     * @param projectId                    the ID of the project
     * @param studentEvaluateProjectRequest the request payload containing project evaluation details
     * @return {@link HttpStatus#OK} if evaluation was successful
     */
    @PostMapping("/{studentId}/projects/{projectId}/evaluate")
    public ResponseEntity<Void> evaluateProject(
            @PathVariable Long studentId,
            @PathVariable Long projectId,
            @RequestBody @Valid StudentEvaluateProjectRequest studentEvaluateProjectRequest
    ) {
        evaluateProjectUseCase.execute(studentId, projectId, studentEvaluateProjectRequest);
        return ResponseEntity.ok().build();
    }
}
