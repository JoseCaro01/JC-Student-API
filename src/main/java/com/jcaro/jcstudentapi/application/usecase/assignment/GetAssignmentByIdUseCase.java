package com.jcaro.jcstudentapi.application.usecase.assignment;


import com.jcaro.jcstudentapi.application.dto.assignment.AssignmentResponse;
import com.jcaro.jcstudentapi.application.exception.assignment.AssignmentNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.AssignmentMapper;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;

/**
 * Use case for retrieving a Course by its id.
 */
public class GetAssignmentByIdUseCase {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public GetAssignmentByIdUseCase(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    /**
     * Executes the retrieval of a assignment by id.
     *
     * @param id the assignment id
     * @return the assignment entity
     * @throws AssignmentNotFoundException if project does not exist
     */
    public AssignmentResponse execute(Long id) {
        return assignmentMapper.domainToAssignmentResponse(assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id)));
    }
}
