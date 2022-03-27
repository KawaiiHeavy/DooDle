package com.doodle;

import com.doodle.models.ERole;
import com.doodle.models.Role;
import com.doodle.models.User;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Component
class DoodleCommandLineRunner implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.save(
                new User(
                        UUID.randomUUID(),
                        "admin1",
                        "admin1@gmail.com",
                        "admin1",
                        "88005553535",
                        null,
                        new HashSet<>()
                )
        );

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ADMIN));
        roles.add(new Role(ERole.STUDENT));
        roles.add(new Role(ERole.TRAINER));
        roles.add(new Role(ERole.USER));
        roles.stream().filter(role -> !roleRepository.existsByName(role.getName())).forEach(role -> roleRepository.save(role));
    }
}