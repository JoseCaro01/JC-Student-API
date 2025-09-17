package com.jcaro.jcstudentapi.application.mapper;

import com.jcaro.jcstudentapi.application.dto.user.UserRequest;
import com.jcaro.jcstudentapi.application.dto.user.UserResponse;
import com.jcaro.jcstudentapi.domain.model.User;

import java.util.Collections;

/**
 * Mapper for converting between UserRequest/UserResponse DTOs and the User domain model.
 * <p>
 * This class resides in the application layer and ensures separation of concerns by
 * translating data to and from the domain without introducing any Spring dependencies.
 * Passwords are only included when creating domain objects and are omitted in responses.
 */
public class UserMapper {

    /**
     * Converts a UserRequest DTO into a User domain object.
     * <p>
     * The ID and creationDate are set to null because they will be generated/assigned
     * elsewhere (e.g., persistence layer or service). Roles are initialized as empty and
     * assigned automatically in the backend.
     *
     * @param dto the UserRequest DTO containing input data
     * @return a new User domain object
     */
    public User requestToDomain(UserRequest dto) {
        return new User(
                null,                    // id is null for new users
                dto.name(),
                dto.lastName(),
                dto.email(),
                dto.password(),          // included for domain creation
                null,                    // creationDate set by backend
                Collections.emptyList()  // roles assigned automatically
        );
    }

    /**
     * Converts a User domain object into a UserResponse DTO.
     * <p>
     * Password is intentionally omitted to prevent exposing sensitive information.
     *
     * @param user the User domain object
     * @return a UserResponse DTO for returning to API clients
     */
    public UserResponse domainToResponse(User user) {
        return new UserResponse(
                user.id(),
                user.name(),
                user.lastName(),
                user.email(),
                user.creationDate(),
                user.roles()             // includes roles in the response
        );
    }
}
