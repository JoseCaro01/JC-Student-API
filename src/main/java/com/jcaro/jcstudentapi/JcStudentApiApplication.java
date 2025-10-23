package com.jcaro.jcstudentapi;


import com.jcaro.jcstudentapi.domain.model.Role;
import com.jcaro.jcstudentapi.domain.model.User;
import com.jcaro.jcstudentapi.domain.repository.RoleRepository;
import com.jcaro.jcstudentapi.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;



@SpringBootApplication
public class JcStudentApiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(JcStudentApiApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
			/*final Role userRole = roleRepository.save(new Role(null, "ROLE_USER"));
			final Role adminRole = roleRepository.save(new Role(null, "ROLE_ADMIN"));
			final User jose = userRepository.save(new User(null, "Jose Alfonso", "Caro Romero", "jcaroromeroprog@gmail.com", passwordEncoder().encode("12345678"),
					LocalDateTime.now(), List.of(adminRole)));*/
		};

	}

}
