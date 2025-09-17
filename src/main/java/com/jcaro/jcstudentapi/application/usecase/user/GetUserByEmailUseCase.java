package com.jcaro.jcstudentapi.application.usecase.user;

import com.jcaro.jcstudentapi.application.dto.user.UserResponse;
import com.jcaro.jcstudentapi.application.exception.user.UserNotFoundException;
import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;

/**
 * Use case responsible for retrieving a user by their email.
 * <p>
 * Part of the Application Layer: interacts with the UserRepository and
 * maps domain entities to response DTOs. Does not know about controllers or persistence.
 */
public class GetUserByEmailUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetUserByEmailUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Executes the use case to fetch a user by email.
     *
     * @param email the email of the user to fetch
     * @return a UserResponse DTO representing the user
     * @throws UserNotFoundException if no user exists with the given email
     */
    public UserResponse execute(String email) {
        return userMapper.domainToResponse(
                userRepository.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new)
        );
    }
}
