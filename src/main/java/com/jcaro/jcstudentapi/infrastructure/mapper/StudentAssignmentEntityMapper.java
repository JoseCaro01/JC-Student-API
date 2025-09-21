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


    private final StudentEntityMapper studentEntityMapper;
    private final AssignmentEntityMapper assignmentEntityMapper;

    /**
     * Constructor that injects CourseEntityMapper to map
     * Course objects within Assignment.
     *
     * @param studentEntityMapper mapper to convert CourseEntity to Course
     * @param assignmentEntityMapper mapper to convert CourseEntity to Course
     */
    public StudentAssignmentEntityMapper(StudentEntityMapper studentEntityMapper, AssignmentEntityMapper assignmentEntityMapper) {
        this.studentEntityMapper = studentEntityMapper;
        this.assignmentEntityMapper = assignmentEntityMapper;
    }
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
               studentEntityMapper.toDomain(entity.getStudent()),
                assignmentEntityMapper.toDomain(entity.getAssignment()),
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
        entity.setStudent(studentEntityMapper.toEntity(domain.student()));
        entity.setAssignment(assignmentEntityMapper.toEntity(domain.assignment()));
        entity.setScore(domain.score());

        return entity;
    }


}
