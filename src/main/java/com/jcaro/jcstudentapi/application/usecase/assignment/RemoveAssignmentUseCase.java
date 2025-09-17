package com.jcaro.jcstudentapi.application.usecase.assignment;

import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;

/**
 * Use case for removing an Assignment by id.
 */
public class RemoveAssignmentUseCase {

    private final AssignmentRepository assignmentRepository;

    public RemoveAssignmentUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Deletes an assignment by its ID.
     *
     * @param id assignment identifier
     * @throws AssignmentNotFoundException if the assignment does not exist
     */
    public void execute(Long id) {
        if (assignmentRepository.findById(id).isEmpty()) {
            throw new AssignmentNotFoundException(id);
        }
        assignmentRepository.deleteById(id);
    }
}
