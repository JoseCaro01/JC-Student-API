package com.jcaro.jcstudentapi.infrastructure.persistence.adapter;

import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import com.jcaro.jcstudentapi.infrastructure.mapper.UserEntityMapper;
import com.jcaro.jcstudentapi.infrastructure.persistence.entity.UserEntity;
import com.jcaro.jcstudentapi.infrastructure.persistence.repository.UserRepositoryJPA;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adapter that implements the domain's UserRepository interface.
 * It translates between the domain model (User) and the JPA entity (UserEntity)
 * and uses the Spring Data JPA repository for persistence.
 */
@Repository
public class UserAdapter implements UserRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final UserEntityMapper userMapper;

    public UserAdapter(UserRepositoryJPA userRepositoryJPA, UserEntityMapper userMapper) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userRepositoryJPA.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryJPA.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        userRepositoryJPA.findByEmail(email);
        return userRepositoryJPA.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryJPA.deleteById(id);
    }
}