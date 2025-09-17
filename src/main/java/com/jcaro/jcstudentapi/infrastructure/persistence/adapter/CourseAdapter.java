package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.domain.repository.CourseRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.CourseEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.CourseEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.CourseRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's CourseRepository interface.
 * It translates between the domain model (Course) and the JPA entity (CourseEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class CourseAdapter implements CourseRepository {

    private final CourseRepositoryJPA courseRepositoryJPA;
    private final CourseEntityMapper courseMapper;

    public CourseAdapter(CourseRepositoryJPA courseRepositoryJPA, CourseEntityMapper courseMapper) {
        this.courseRepositoryJPA = courseRepositoryJPA;
        this.courseMapper = courseMapper;
    }

    @Override
    public Course save(Course course) {
        // 1. Convert the domain model to a JPA entity.
        CourseEntity courseEntity = courseMapper.toEntity(course);
        // 2. Use the JPA repository to save the entity.
        CourseEntity savedEntity = courseRepositoryJPA.save(courseEntity);
        // 3. Convert the saved entity back to a domain model and return it.
        return courseMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Course> findById(Long id) {
        // Find the entity and map it to the domain model if present.
        return courseRepositoryJPA.findById(id).map(courseMapper::toDomain);
    }

    @Override
    public List<Course> findAll() {
        // Find all entities, stream them, and map each to a domain model.
        return courseRepositoryJPA.findAll().stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepositoryJPA.deleteById(id);
    }
}