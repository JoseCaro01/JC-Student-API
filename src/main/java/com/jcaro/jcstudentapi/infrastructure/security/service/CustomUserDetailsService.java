package com.jcaro.jcstudentapi.infrastructure.security.service;

import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user with the given username
        Optional<User> optionalUser = userRepository.findByEmail(username);
        // Check if user exists
        if (optionalUser.isEmpty()) {
            //  logger.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            final User user = optionalUser.get();
            // log.info("User found in the database: {}", username);
            // Create a collection of SimpleGrantedAuthority objects from the user's roles
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.roles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.name()));
            });
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.email(), user.password(), authorities);
        }

    }
}

