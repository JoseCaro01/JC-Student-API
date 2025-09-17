package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentAssignmentEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between StudentAssignmentEntity (JPA) and StudentAssignment (domain).
 * Keeps the domain model independent from persistence concerns.
 */
@Component
public class StudentAssignmentEntityMapper {

    /**
     * Converts a StudentAssignmentEntity to a StudentAssignment domain model.
     * @param entity the StudentAssignmentEntity to convert
     * @return the corresponding StudentAssignment domain model
     */
    public StudentAssignment toDomain(StudentAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }


        return new StudentAssignment(
                entity.getId(),
                null, // Avoid for cyclic dependency
                null, // Avoid for cyclic dependency
                entity.getScore()
        );
    }

    /**
     * Converts a StudentAssignment domain model to a StudentAssignmentEntity.
     * @param domain the StudentAssignment domain model to convert
     * @return the corresponding StudentAssignmentEntity
     */
    public StudentAssignmentEntity toEntity(StudentAssignment domain) {
        if (domain == null) {
            return null;
        }

        StudentAssignmentEntity entity = new StudentAssignmentEntity();
        entity.setId(domain.id());
        entity.setScore(domain.score());

        // Student y Assignment se deberían mapear con mappers específicos
        return entity;
    }


}
