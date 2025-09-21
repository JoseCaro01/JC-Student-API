package com.jcaro.jcstudentapi.infrastructure.persistence.repository;


import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJPA extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByCoursesId(Long courseId);
    @Query("SELECT sa.student FROM StudentAssignmentEntity sa WHERE sa.assignment.id = :assignmentId")
    List<StudentEntity> findByAssignmentId(@Param("assignmentId") Long assignmentId);
    @Query("SELECT sp.student FROM StudentProjectEntity sp WHERE sp.project.id = :projectId")
    List<StudentEntity> findByProjectId(@Param("projectId") Long projectId);
}
