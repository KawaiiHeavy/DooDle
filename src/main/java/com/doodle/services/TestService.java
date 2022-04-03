package com.doodle.services;

import com.doodle.models.Test;
import com.doodle.models.TestInput;
import com.doodle.repostitories.TestRepository;
import com.doodle.repostitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestService {

    private TestRepository testRepository;

    private UserRepository userRepository;

    public Test createTest(TestInput testInput){
        Test test = new Test();
        test.setCreator(userRepository.findById(testInput.getCreatorId()).get());
        test.setTitle(testInput.getTitle());
        test.setMaxBall(testInput.getMaxBall());
        test.setSeconds(testInput.getSeconds());
        return testRepository.save(test);
    }

    public Set<Test> findTests(String input){
        List<Test> testsList = testRepository.findByTitle(input);
        Test testById = testRepository.findById(UUID.fromString(input)).get();
        testsList.add(testById);
        return new HashSet<>(testsList);
    }
}
