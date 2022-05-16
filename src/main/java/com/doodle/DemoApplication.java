package com.doodle;

import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.services.TestService;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.beans.Transient;
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

        testCreation(10);

        Test test = (Test)testService.findTests("test1").toArray()[0];

//        testService.deleteTest(test);
    }

    public void roleCreation(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ADMIN));
        roles.add(new Role(ERole.TRAINER));
        roles.add(new Role(ERole.STUDENT));
        roles.add(new Role(ERole.USER));

        roles.stream().filter(role -> !roleRepository.existsByName(role.getName())).forEach(role -> roleRepository.save(role));
    }

    public Set<User> userCreation(int count){

        Set<User> users = new HashSet<>();

        for (int i = 0; i < count; ++i){

            String prefix = getPrefixForCreation();

            User user = new User(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new StringBuffer(prefix).append(i).append("@gmail.com").toString(),
                    new StringBuffer(prefix).append(i).toString(),
                    getRandomPhone(),
                    null,
                    convertRoleToPrivileges(prefix.toUpperCase()));

            users.add(user);
        }
        return users;
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

        Set<User> members = userCreation(10);
        Set<Result> results = resultsCreation(10);

        User leader = (User)members.toArray()[0];

        System.out.println(leader);

        for (int i = 0; i < count; ++i){
            Test test = new Test();

            test.setId(UUID.randomUUID());
            test.setMaxBall((double) new Random().nextInt(100));
            test.setSeconds(new Random().nextInt(100));
            test.setMembers(members);
            test.setCreator(leader);
            test.setTitle(new StringBuffer(prefix).append(i).toString());
            test.setQuestions(questionCreation(10, 10));

            testService.createTest(test);

            for (Result result: results){
                result.setParticipant(leader);
                test.addResult(result);
            }

            testService.createTest(test);

        }

    }

    public Set<Question> questionCreation(int questionCount, int answerCount){
        String prefix = "question";

        Set<Question> questions = new HashSet<>();
        Set<Answer> answers = answerCreation(answerCount);

        for (int i = 0; i < questionCount; ++i){
            UUID questionId = UUID.randomUUID();
            Question question = new Question(
                    questionId,
                    new StringBuffer(prefix).append(i).toString(),
                    15 * new Random().nextDouble());
            for (Answer answer: answers){
                question.addAnswer(answer);
            }
            questions.add(question);
        }
        return questions;
    }

    public Set<Answer> answerCreation(int count){
        String prefix = "answer";
        Set<Answer> answers = new HashSet<>();
        for (int i = 0; i < count; ++i){
            answers.add(new Answer(
                    UUID.randomUUID(),
                    new StringBuffer(prefix).append(i).toString(),
                    new Random().nextBoolean()
            ));
        }
        return answers;
    }

    public Set<Result> resultsCreation(int count){

        Set<Result> results = new HashSet<>();
        for (int i = 0; i < count; ++i){
            results.add(new Result(UUID.randomUUID(),15 * new Random().nextDouble()));
        }
        return results;
    }
}