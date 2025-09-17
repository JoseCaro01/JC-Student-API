package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.Role;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Role data.
 * This is a "port" in the hexagonal architecture, defining the contract for
 * data persistence in the domain layer. It remains independent of any
 * specific database technology like JPA.
 */
public interface RoleRepository {

    /**
     * Saves a new role or updates an existing one.
     *
     * @param role the role to be saved.
     * @return the saved role, which may have been updated with an ID from the persistence layer.
     */
    Role save(Role role);

    /**
     * Finds a role by its unique ID.
     *
     * @param id the ID of the role.
     * @return an {@link Optional} containing the role if found, or {@link Optional#empty()} if not.
     */
    Optional<Role> findById(Long id);

    /**
     * Retrieves all roles.
     *
     * @return a list of all roles.
     */
    List<Role> findAll();

    /**
     * Deletes a role by its unique ID.
     *
     * @param id the ID of the role to be deleted.
     */
    void deleteById(Long id);

}