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
                null, // Avoid for cyclic dependency
                null, // Project -> Avoid for cyclic dependency
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
        entity.setCodeQuality(domain.codeQuality());
        entity.setFunctionality(domain.functionality());
        entity.setSecurity(domain.security());
        entity.setDocumentation(domain.documentation());
        entity.setDeployment(domain.deployment());
        entity.setExtra(domain.extra());


        return entity;
    }


}
