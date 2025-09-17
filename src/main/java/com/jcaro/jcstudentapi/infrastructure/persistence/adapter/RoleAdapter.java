package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.Role;
import com.jcaro.jcstudentapi.domain.repository.RoleRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.RoleEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.RoleEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.RoleRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain's RoleRepository interface.
 * It translates between the domain model (Role) and the JPA entity (RoleEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class RoleAdapter implements RoleRepository {

    private final RoleRepositoryJPA roleRepositoryJPA;
    private final RoleEntityMapper roleMapper;

    public RoleAdapter(RoleRepositoryJPA roleRepositoryJPA, RoleEntityMapper roleMapper) {
        this.roleRepositoryJPA = roleRepositoryJPA;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleMapper.toEntity(role);
        RoleEntity savedEntity = roleRepositoryJPA.save(roleEntity);
        return roleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepositoryJPA.findById(id)
                .map(roleMapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return roleRepositoryJPA.findAll().stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        roleRepositoryJPA.deleteById(id);
    }
}