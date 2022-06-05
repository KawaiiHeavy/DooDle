package com.doodle.controllers;

import com.doodle.models.Question;
import com.doodle.models.ERole;
import com.doodle.models.Role;
import com.doodle.models.Test;
import com.doodle.models.TestInput;
import com.doodle.services.TestService;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @PostMapping("/createTest")
    public Test createTest(@RequestBody TestInput test){
        Role role1 = new Role(ERole.USER);
        for(Role role : userService.getUserById(test.getCreatorId()).getRoles()) {
            role1 = role;
        }
        if (role1.getName().equals(ERole.ADMIN) || role1.getName().equals(ERole.TRAINER))
            return testService.createTest(test);
        else return null;
    }

    @GetMapping("/findTests/{input}")
    public Set<Test> findTests(@PathVariable String input) {
        return testService.findTests(input);
    }

    @PostMapping("/createQuestions")
    public Set<Question> createQuestions(@RequestBody Set<Question> questions){
        return testService.createQuestions(questions);
    }

    @GetMapping("/getTests")
    public Set<Test> getTests(){
        return testService.getTests();
    }
}
