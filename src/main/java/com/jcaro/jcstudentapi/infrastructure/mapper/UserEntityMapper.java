package com.jcaro.jcstudentapi.infrastructure.mapper;

import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

/**
 * Maps between UserEntity (JPA) and User (domain).
 *
 * <p>This mapper handles conversion between the persistence model (UserEntity)
 * and the domain model (User), including nested Role objects using RoleEntityMapper.
 * It ensures the domain model remains independent of persistence concerns.</p>
 */
@Component
public class UserEntityMapper {

    private final RoleEntityMapper roleMapper;

    /**
     * Constructor that injects RoleEntityMapper.
     *
     * @param roleMapper mapper used to convert RoleEntity to Role domain objects
     */
    public UserEntityMapper(RoleEntityMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * Converts a UserEntity to a User domain model.
     *
     * @param entity the UserEntity to convert
     * @return the corresponding User domain model, or null if the entity is null
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreationDate(),
                entity.getRoles().stream()
                        .map(roleMapper::toDomain)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts a User domain model to a UserEntity.
     *
     * @param domain the User domain model to convert
     * @return the corresponding UserEntity, or null if the domain model is null
     */
    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(domain.id());
        entity.setName(domain.name());
        entity.setLastName(domain.lastName());
        entity.setEmail(domain.email());
        entity.setPassword(domain.password());
        entity.setCreationDate(domain.creationDate());
        entity.setRoles(domain.roles().stream()
                .map(roleMapper::toEntity)
                .collect(Collectors.toList()));
        return entity;
    }
}
