package com.jcaro.jcstudentapi.domain.repository;

import com.jcaro.jcstudentapi.domain.model.User;
import java.util.Optional;

/**
 * Repository interface for managing User data.
 * This is a "port" in the hexagonal architecture, defining the contract for
 * data persistence in the domain layer. It remains independent of any
 * specific database technology like JPA.
 */
public interface UserRepository {

    /**
     * Saves a new user or updates an existing one.
     *
     * @param user the user to be saved.
     * @return the saved user, which may have been updated with an ID from the persistence layer.
     */
    User save(User user);

    /**
     * Finds a user by their unique ID.
     *
     * @param id the ID of the user.
     * @return an {@link Optional} containing the user if found, or {@link Optional#empty()} if not.
     */
    Optional<User> findById(Long id);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user.
     * @return an {@link Optional} containing the user if found, or {@link Optional#empty()} if not.
     */
    Optional<User> findByEmail(String email);

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to be deleted.
     */
    void deleteById(Long id);
}