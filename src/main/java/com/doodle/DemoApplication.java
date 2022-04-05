package com.doodle;

import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.TestService;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

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
    TestService testService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Creating roles
        roleCreation();

        userCreation(10);

        testCreation(10);

        Set<Question> questions = questionCreation(10, 10);

        for (Question question : questions){
            answerCreation(question, 10);
        }

    }

    public void roleCreation(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ADMIN));
        roles.add(new Role(ERole.TRAINER));
        roles.add(new Role(ERole.STUDENT));
        roles.add(new Role(ERole.USER));

        roles.stream().filter(role -> !roleRepository.existsByName(role.getName())).forEach(role -> roleRepository.save(role));
    }

    public void userCreation(int count){
        for (int i = 0; i < count; ++i){

            String prefix = getPrefixForCreation();

            userService.save(new User(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new StringBuffer(prefix).append(i).append("@gmail.com").toString(),
                    new StringBuffer(prefix).append(i).toString(),
                    getRandomPhone(),
                    null,
                    convertRoleToPrivileges(prefix.toUpperCase())
            ));
        }
    }

    public String getPrefixForCreation(){
        Random random = new Random();
        int choice = random.nextInt(4);
        String prefix;

        switch (choice){
            case 0:
                return "user";
            case 1:
                return "admin";
            case 2:
                return "trainer";
            case 3:
                return "student";
        }
        return "user";
    }

    public String getRandomPhone(){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 10; i++){
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public Set<Role> convertRoleToPrivileges(String role){
        Set<Role> privileges = new HashSet<>();
        switch (role){
            case "ADMIN":
                privileges.add(roleRepository.findByName(ERole.ADMIN).get());

            case "TRAINER":
                privileges.add(roleRepository.findByName(ERole.TRAINER).get());

            case "STUDENT":
                privileges.add(roleRepository.findByName(ERole.STUDENT).get());

            default:
                privileges.add(roleRepository.findByName(ERole.USER).get());
                return privileges;
        }
    }

    public void testCreation(int count){
        String prefix = "test";
        for (int i = 0; i < count; ++i){
            testService.createTest(new TestInput(
                    new StringBuffer("test").append(i).toString(),
                    userService.getRandomUser().get().getId(),
                    (double) new Random().nextInt(100),
                    new Random().nextInt(100)
            ));
        }
    }

    public Set<Question> questionCreation(int questionCount, int answerCount){
        String prefix = "question";
        Set<Question> result = new HashSet<>();
        for (int i = 0; i < questionCount; ++i){
            Set<Question> questions = new HashSet<>();
            UUID questionId = UUID.randomUUID();
            questions.add(new Question(
                    questionId,
                    new StringBuffer(prefix).append(i).toString(),
                    15 * new Random().nextDouble()
            ));
            testService.createQuestions(questions);
            result.addAll(questions);
        }
        return result;
    }

    public void answerCreation(Question question, int count){
        String prefix = "answer";
        Set<Answer> answers = new HashSet<>();
        for (int i = 0; i < count; ++i){
            answers.add(new Answer(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new Random().nextBoolean()
            ));
        }
        testService.createAnswers(answers, question);
    }
}