package com.jcaro.jcstudentapi.infrastructure.persistence.repository;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepositoryJPA extends JpaRepository<ProjectEntity,Long> {
    List<ProjectEntity> findByCourseId(Long courseId);

}
