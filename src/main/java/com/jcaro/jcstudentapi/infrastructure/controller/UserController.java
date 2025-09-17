package com.jcaro.jcstudentapi.infrastructure.controller;

import com.jcaro.jcstudentapi.application.dto.user.UserRequest;
import com.jcaro.jcstudentapi.application.dto.user.UserResponse;
import com.jcaro.jcstudentapi.application.usecase.user.CreateUserUseCase;
import com.jcaro.jcstudentapi.application.usecase.user.EditUserUseCase;
import com.jcaro.jcstudentapi.application.usecase.user.GetUserByEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

/**
 * REST controller for managing users.
 * <p>
 * This controller belongs to the Infrastructure layer and acts as the inbound adapter,
 * exposing HTTP endpoints for user-related operations. It delegates business logic
 * to the corresponding application use cases.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final EditUserUseCase editUserUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            EditUserUseCase editUserUseCase,
            GetUserByEmailUseCase getUserByEmailUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.editUserUseCase = editUserUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    /**
     * Creates a new user in the system.
     *
     * @param user the user request payload with the required information
     * @return the created user wrapped in {@link UserResponse}
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest user) {
        UserResponse createdUser = createUserUseCase.execute(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves the currently authenticated user by their email.
     *
     * @param authentication Spring Security authentication object containing the current principal
     * @return the authenticated user's details wrapped in {@link UserResponse}
     */
    @GetMapping
    public ResponseEntity<UserResponse> getUser(Authentication authentication) {
        UserResponse user = getUserByEmailUseCase.execute(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Updates an existing user.
     * <p>
     * Only admins or the user themselves can perform this operation.
     *
     * @param authentication Spring Security authentication object containing the current principal
     * @param id             the ID of the user to update
     * @param user           the request payload with updated user information
     * @return the updated user wrapped in {@link UserResponse}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(Authentication authentication,
                                                   @PathVariable Long id,
                                                   @RequestBody UserRequest user) {
        UserResponse updatedUser = editUserUseCase.execute(authentication, id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
