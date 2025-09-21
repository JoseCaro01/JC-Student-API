package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.StudentProject;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.StudentProjectEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps between StudentProjectEntity (JPA) and StudentProject (domain).
 * Keeps the domain model free from persistence details.
 */
@Component
public class StudentProjectEntityMapper {

    private final StudentEntityMapper studentEntityMapper;
    private final ProjectEntityMapper projectEntityMapper;

    /**
     * Constructor that injects CourseEntityMapper to map
     * Course objects within Assignment.
     *
     * @param studentEntityMapper mapper to convert CourseEntity to Course
     * @param projectEntityMapper mapper to convert CourseEntity to Course
     */
    public StudentProjectEntityMapper(StudentEntityMapper studentEntityMapper, ProjectEntityMapper projectEntityMapper) {
        this.studentEntityMapper = studentEntityMapper;
        this.projectEntityMapper = projectEntityMapper;
    }

    /**
     * Converts a StudentProjectEntity to a StudentProject domain model.
     *
     * @param entity the StudentProjectEntity to convert
     * @return the corresponding StudentProject domain model
     */
    public StudentProject toDomain(StudentProjectEntity entity) {
        if (entity == null) {
            return null;
        }


        return new StudentProject(
                entity.getId(),
                studentEntityMapper.toDomain(entity.getStudent()),
                projectEntityMapper.toDomain(entity.getProject()),
                entity.getCodeQuality(),
                entity.getFunctionality(),
                entity.getSecurity(),
                entity.getDocumentation(),
                entity.getDeployment(),
                entity.getExtra()
        );
    }

    /**
     * Converts a StudentProject domain model to a StudentProjectEntity.
     *
     * @param domain the StudentProject domain model to convert
     * @return the corresponding StudentProjectEntity
     */
    public StudentProjectEntity toEntity(StudentProject domain) {
        if (domain == null) {
            return null;
        }

        StudentProjectEntity entity = new StudentProjectEntity();
        entity.setId(domain.id());
        entity.setStudent(studentEntityMapper.toEntity(domain.student()));
        entity.setProject(projectEntityMapper.toEntity(domain.project()));
        entity.setCodeQuality(domain.codeQuality());
        entity.setFunctionality(domain.functionality());
        entity.setSecurity(domain.security());
        entity.setDocumentation(domain.documentation());
        entity.setDeployment(domain.deployment());
        entity.setExtra(domain.extra());


        return entity;
    }


}
