package com.doodle;

import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.impl.QuestionServiceImpl;
import com.doodle.services.impl.TestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.*;

@SpringBootApplication
public class DoodleApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoodleApplication.class, args);
    }
}

@Component
@AllArgsConstructor
class DoodleCommandLineRunner implements CommandLineRunner {

    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        roleCreation();
    }

    public void roleCreation() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ADMIN));
        roles.add(new Role(ERole.TRAINER));
        roles.add(new Role(ERole.STUDENT));
        roles.add(new Role(ERole.USER));

        roles.stream().filter(role -> !roleRepository.existsByName(role.getName())).forEach(role -> roleRepository.save(role));
    }

}