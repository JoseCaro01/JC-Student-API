package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.Role;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;


/**
 * Maps between RoleEntity (JPA) and Role (domain).
 *
 * <p>This mapper ensures that the domain model remains independent
 * of persistence concerns. It provides methods to convert from
 * RoleEntity to Role and vice versa.</p>
 */
@Component
public class RoleEntityMapper {

    /**
     * Converts a RoleEntity to a Role domain model.
     *
     * @param entity the RoleEntity to convert
     * @return the corresponding Role domain model, or null if the entity is null
     */
    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Role(
                entity.getId(),
                entity.getName()
        );
    }

    /**
     * Converts a Role domain model to a RoleEntity.
     *
     * @param domain the Role domain model to convert
     * @return the corresponding RoleEntity, or null if the domain model is null
     */
    public RoleEntity toEntity(Role domain) {
        if (domain == null) {
            return null;
        }
        RoleEntity entity = new RoleEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());
        return entity;
    }
}
