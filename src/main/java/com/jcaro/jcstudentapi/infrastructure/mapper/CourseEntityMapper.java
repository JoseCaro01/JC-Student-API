package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.Course;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Maps between AssignmentEntity (JPA) and Assignment (domain).
 * This mapper acts as a bridge, keeping the domain model clean of persistence concerns.
 */
@Component
public class CourseEntityMapper {

    /**
     * Converts an CourseEntity to an Course domain model.
     *
     * @param entity the CourseEntity to convert
     * @return the corresponding Couse domain model
     */
    public Course toDomain(CourseEntity entity) {
        if (entity == null) {
            return null;
        }


        return new Course(
                entity.getId(),
                entity.getName(),
                entity.getProgrammingLanguage()

        );
    }

    /**
     * Converts an Course domain model to an CourseEntity.
     *
     * @param domain the Course domain model to convert
     * @return the corresponding CourseEntity
     */
    public CourseEntity toEntity(Course domain) {
        if (domain == null) {
            return null;
        }

        CourseEntity entity = new CourseEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());
        entity.setProgrammingLanguage(domain.programmingLanguage());

        return entity;
    }
}