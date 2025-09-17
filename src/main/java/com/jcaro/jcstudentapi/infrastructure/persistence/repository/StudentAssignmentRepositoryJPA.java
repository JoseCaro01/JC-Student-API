package com.jcaro.jcstudentapi.infrastructure.persistence.repository;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentAssignmentRepositoryJPA extends JpaRepository<StudentAssignmentEntity, Long> {
    Optional<StudentAssignmentEntity> findByStudentIdAndAssignmentId(Long studentId, Long assigmentId);

}
