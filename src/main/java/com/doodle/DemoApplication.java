package com.doodle;

import com.doodle.models.User;
import com.doodle.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            User user1 = userService.save(
                    new User(
                            UUID.randomUUID(),
                            "admin",
                            "admin@gmail.com",
                            "admin",
                            "88005553535",
                            User.UserRole.ADMIN,
                            null
                    )
            );
        };
    }

}
