package com.jcaro.jcstudentapi.application.usecase.user;

import com.jcaro.jcstudentapi.application.dto.user.UserRequest;
import com.jcaro.jcstudentapi.application.dto.user.UserResponse;
import com.jcaro.jcstudentapi.application.exception.user.UnauthorizedUserUpdateException;
import com.jcaro.jcstudentapi.application.exception.user.UserNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import org.springframework.security.core.Authentication;

/**
 * Use case responsible for editing an existing user's information.
 * <p>
 * Ensures that the user exists, validates authorization (admin or owner),
 * updates the allowed fields, and returns a response DTO.
 * <p>
 * Part of the Application Layer: coordinates domain entities and repositories
 * without knowing about controllers or persistence details.
 */
public class EditUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public EditUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Executes the use case to update a user's basic information.
     *
     * @param authentication the Spring Security authentication object of the requester
     * @param id             the ID of the user to update
     * @param user           the input DTO containing new user information
     * @return a UserResponse DTO representing the updated user
     * @throws UserNotFoundException           if no user exists with the given ID
     * @throws UnauthorizedUserUpdateException if the authenticated user is neither an admin nor the owner
     */
    public UserResponse execute(Authentication authentication, Long id, UserRequest user) {
        // Fetch existing user or throw exception if not found
        User oldUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        // Validate authorization: must be admin or the user themselves
        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                && !authentication.getName().equals(oldUser.email())) {
            throw new UnauthorizedUserUpdateException();
        }

        // Update allowed fields
        oldUser = oldUser.withName(user.name());
        oldUser = oldUser.withLastName(user.lastName());

        // Save updated user and map to response DTO
        return userMapper.domainToResponse(userRepository.save(oldUser));
    }
}
