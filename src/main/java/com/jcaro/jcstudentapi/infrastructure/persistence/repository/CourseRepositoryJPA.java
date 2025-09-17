package com.jcaro.jcstudentapi.infrastructure.persistence.repository;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryJPA extends JpaRepository<CourseEntity, Long> {

}
