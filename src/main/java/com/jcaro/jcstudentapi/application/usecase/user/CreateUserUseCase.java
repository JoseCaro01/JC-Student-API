package com.jcaro.jcstudentapi.application.usecase.user;

import com.jcaro.jcstudentapi.application.dto.user.UserRequest;
import com.jcaro.jcstudentapi.application.dto.user.UserResponse;
import com.jcaro.jcstudentapi.application.exception.user.EmailAlreadyExistsException;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.domain.repository.RoleRepository;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Use case responsible for creating a new user in the system.
 * <p>
 * Validates that the email is unique, encodes the password, assigns default roles,
 * and sets the creation timestamp.
 * <p>
 * This class belongs to the Application Layer and orchestrates domain entities and repositories,
 * without being aware of web or persistence concerns.
 */
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public CreateUserUseCase(UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder,
                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Executes the use case to create a new user.
     *
     * @param user the input DTO containing user information
     * @return a UserResponse DTO representing the saved user (without password)
     * @throws EmailAlreadyExistsException if a user with the same email already exists
     */
    public UserResponse execute(UserRequest user) {
        // Ensure email is unique
        userRepository.findByEmail(user.email())
                .ifPresent(u -> { throw new EmailAlreadyExistsException(u.email()); });

        // Map DTO to domain model
        User newUser = userMapper.requestToDomain(user);

        // Assign default role (e.g., ROLE_USER)
        newUser = newUser.withRoles(List.of(roleRepository.findById(1L).orElseThrow()));

        // Encode password
        newUser = newUser.withPassword(passwordEncoder.encode(user.password()));

        // Set creation timestamp
        newUser = newUser.withCreationDate(LocalDateTime.now());

        // Save user and map to response DTO
        return userMapper.domainToResponse(userRepository.save(newUser));
    }
}
