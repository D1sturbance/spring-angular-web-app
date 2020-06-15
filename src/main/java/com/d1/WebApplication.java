package com.d1;

import com.d1.domain.User;
import com.d1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            User user = new User(
                    "Vardenis",
                    "Pavardenis",
                    LocalDate.of(1993, 10, 3),
                    "vardenis@gmail.com",
                    "+37062588954");
            userRepository.save(user);
        };
    }
}
