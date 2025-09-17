package com.jcaro.jcstudentapi.infrastructure.config;


import com.jcaro.jcstudentapi.application.mapper.UserMapper;
import com.jcaro.jcstudentapi.application.usecase.user.CreateUserUseCase;
import com.jcaro.jcstudentapi.application.usecase.user.EditUserUseCase;
import com.jcaro.jcstudentapi.application.usecase.user.GetUserByEmailUseCase;
import com.jcaro.jcstudentapi.domain.repository.RoleRepository;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        return new CreateUserUseCase(userRepository, roleRepository, passwordEncoder, userMapper);
    }

    @Bean
    public EditUserUseCase editUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        return new EditUserUseCase(userRepository, userMapper);
    }

    @Bean
    public GetUserByEmailUseCase getUserByEmailUseCase(UserRepository userRepository, UserMapper userMapper) {
        return new GetUserByEmailUseCase(userRepository, userMapper);
    }

}



