package com.jcaro.jcstudentapi.infrastructure.persistence.repository;


import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJPA extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByCoursesId(Long courseId);
    List<StudentEntity> findByStudentAssignmentListId(Long assignmentId);
    List<StudentEntity> findByStudentProjectListId(Long projectId);
}
