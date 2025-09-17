package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentRequest;
import com.jcaro.jcstudentapi.application.usecase.assignment.CreateAssignmentUseCase;
import com.jcaro.jcstudentapi.application.usecase.assignment.EditAssignmentUseCase;
import com.jcaro.jcstudentapi.application.usecase.assignment.GetAllAssignmentsUseCase;
import com.jcaro.jcstudentapi.application.usecase.assignment.RemoveAssignmentUseCase;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing assignments.
 * <p>
 * This controller belongs to the Infrastructure layer and acts as the inbound adapter,
 * exposing HTTP endpoints for assignment-related operations.
 * It delegates the business logic to the corresponding use cases in the Application layer.
 */
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final CreateAssignmentUseCase createAssignmentUseCase;
    private final EditAssignmentUseCase editAssignmentUseCase;
    private final GetAllAssignmentsUseCase getAllAssignmentsUseCase;
    private final RemoveAssignmentUseCase removeAssignmentUseCase;

    public AssignmentController(
            CreateAssignmentUseCase createAssignmentUseCase,
            EditAssignmentUseCase editAssignmentUseCase,
            GetAllAssignmentsUseCase getAllAssignmentsUseCase,
            RemoveAssignmentUseCase removeAssignmentUseCase) {
        this.createAssignmentUseCase = createAssignmentUseCase;
        this.editAssignmentUseCase = editAssignmentUseCase;
        this.getAllAssignmentsUseCase = getAllAssignmentsUseCase;
        this.removeAssignmentUseCase = removeAssignmentUseCase;
    }

    /**
     * Creates a new assignment.
     *
     * @param assignment the request payload containing assignment details
     * @return the created {@link Assignment}
     */
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody @Valid AssignmentRequest assignment) {
        Assignment createdAssignment = createAssignmentUseCase.execute(assignment);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    /**
     * Updates an existing assignment by its ID.
     *
     * @param id         the ID of the assignment to update
     * @param assignment the request payload containing updated assignment details
     * @return the updated {@link Assignment}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody @Valid AssignmentRequest assignment) {
        Assignment updatedAssignment = editAssignmentUseCase.execute(id, assignment);
        return new ResponseEntity<>(updatedAssignment, HttpStatus.OK);
    }

    /**
     * Retrieves all assignments, optionally filtered by course ID.
     *
     * @param courseId optional ID of the course to filter assignments
     * @return a list of {@link Assignment}
     */
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments(@RequestParam("courseId") Optional<Long> courseId) {
        List<Assignment> assignments = getAllAssignmentsUseCase.execute(courseId);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    /**
     * Removes an assignment by its ID.
     *
     * @param id the ID of the assignment to remove
     * @return {@link HttpStatus#NO_CONTENT} if deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAssignment(@PathVariable Long id) {
        removeAssignmentUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
