package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.domain.repository.StudentAssignmentRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.StudentAssignmentEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentAssignmentEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.StudentAssignmentRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's StudentAssignmentRepository interface.
 * It translates between the domain model (StudentAssignment) and the JPA entity (StudentAssignmentEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class StudentAssignmentAdapter implements StudentAssignmentRepository {

    private final StudentAssignmentRepositoryJPA studentAssignmentJpaRepository;
    private final StudentAssignmentEntityMapper mapper;

    public StudentAssignmentAdapter(StudentAssignmentRepositoryJPA studentAssignmentJpaRepository,
                                    StudentAssignmentEntityMapper mapper) {
        this.studentAssignmentJpaRepository = studentAssignmentJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentAssignment save(StudentAssignment studentAssignment) {
        StudentAssignmentEntity entity = mapper.toEntity(studentAssignment);
        StudentAssignmentEntity savedEntity = studentAssignmentJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<StudentAssignment> saveAll(List<StudentAssignment> studentAssignments) {
        final List<StudentAssignmentEntity> studentAssignmentEntities = studentAssignments.stream().map(mapper::toEntity).toList();
        final List<StudentAssignmentEntity> savedEntities = studentAssignmentJpaRepository.saveAll(studentAssignmentEntities);
        return savedEntities.stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<StudentAssignment> findById(Long id) {
        return studentAssignmentJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<StudentAssignment> findAll() {
        return studentAssignmentJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentAssignment> findByStudentIdAndAssignmentId(Long studentId, Long assignmentId) {
        return studentAssignmentJpaRepository.findByStudentIdAndAssignmentId(studentId, assignmentId)
                .map(mapper::toDomain);
    }

    @Override
    public List<StudentAssignment> findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return studentAssignmentJpaRepository.findByStudentIdAndAssignmentCourseId(studentId, courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentAssignmentJpaRepository.deleteById(id);
    }
}
