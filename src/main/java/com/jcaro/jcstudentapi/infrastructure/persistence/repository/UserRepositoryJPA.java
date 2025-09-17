package com.jcaro.jcstudentapi.infrastructure.persistence.repository;

import com.jcaro.jcstudentapi.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user by their email address.
     * Spring Data JPA automatically generates the query for this method.
     *
     * @param email The email address of the user.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    Optional<UserEntity> findByEmail(String email);
}