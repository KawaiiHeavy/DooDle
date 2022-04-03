package com.doodle.controllers;

import com.doodle.models.Test;
import com.doodle.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/createTest")
    public Test createTest(@RequestBody Test test){

        return testService.createTest(test);
    }

    @GetMapping("/findTest")
    public Set<Test> findTest(String input) {
        return testService.findTest(input);
    }
}
