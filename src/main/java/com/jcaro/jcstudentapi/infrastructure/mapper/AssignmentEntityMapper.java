package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.AssignmentEntity;
import org.springframework.stereotype.Component;
import java.util.Collections;

/**
 * Maps between AssignmentEntity (JPA) and Assignment (domain).
 * This mapper acts as a bridge, keeping the domain model clean of persistence concerns.
 */
@Component
public class AssignmentEntityMapper {


    private final CourseEntityMapper courseEntityMapper;

    /**
     * Constructor that injects CourseEntityMapper to map
     * Course objects within Assignment.
     *
     * @param courseEntityMapper mapper to convert CourseEntity to Course
     */
    public AssignmentEntityMapper(CourseEntityMapper courseEntityMapper) {
        this.courseEntityMapper = courseEntityMapper;
    }

    /**
     * Converts an AssignmentEntity to an Assignment domain model.
     * @param entity the AssignmentEntity to convert
     * @return the corresponding Assignment domain model
     */
    public Assignment toDomain(AssignmentEntity entity) {
        if (entity == null) {
            return null;
        }


        return new Assignment(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isObligatory(),
                courseEntityMapper.toDomain(entity.getCourse()),
                Collections.emptyList() // Avoid for cyclic dependency
        );
    }

    /**
     * Converts an Assignment domain model to an AssignmentEntity.
     * @param domain the Assignment domain model to convert
     * @return the corresponding AssignmentEntity
     */
    public AssignmentEntity toEntity(Assignment domain) {
        if (domain == null) {
            return null;
        }

        AssignmentEntity entity = new AssignmentEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());
        entity.setDescription(domain.description());
        entity.setObligatory(domain.obligatory());
        entity.setCourse(courseEntityMapper.toEntity(domain.course()));
        return entity;
    }
}