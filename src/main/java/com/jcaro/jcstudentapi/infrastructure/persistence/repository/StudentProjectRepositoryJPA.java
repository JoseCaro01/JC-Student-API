package com.jcaro.jcstudentapi.infrastructure.persistence.repository;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.ProjectEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentAssignmentEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentProjectRepositoryJPA extends JpaRepository<StudentProjectEntity, Long> {
    Optional<StudentProjectEntity> findByStudentIdAndProjectId(Long studentId, Long projectId);
    List<StudentProjectEntity> findByStudentIdAndProjectCourseId(Long studentId, Long courseId);

}
