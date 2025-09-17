package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.repository.AssignmentRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.AssignmentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.AssignmentEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.AssignmentRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's UserRepository interface.
 * It translates between the domain model (User) and the JPA entity (UserEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class AssignmentAdapter implements AssignmentRepository {

    private final AssignmentRepositoryJPA assignmentJpaRepository;
    private final AssignmentEntityMapper mapper;

    public AssignmentAdapter(AssignmentRepositoryJPA assignmentJpaRepository, AssignmentEntityMapper mapper) {
        this.assignmentJpaRepository = assignmentJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Assignment save(Assignment assignment) {
        AssignmentEntity entity = mapper.toEntity(assignment);
        AssignmentEntity savedEntity = assignmentJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Assignment> findById(Long id) {
        return assignmentJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Assignment> findByCourseId(Long courseId) {
        return assignmentJpaRepository.findByCourseId(courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        assignmentJpaRepository.deleteById(id);
    }
}