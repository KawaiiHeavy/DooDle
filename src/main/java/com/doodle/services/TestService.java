package com.doodle.services;

import com.doodle.models.Test;
import com.doodle.repostitories.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestService {

    private TestRepository testRepository;

    public Test createTest(Test test){
        return testRepository.save(test);
    }

    public Set<Test> findTests(String input){
        List<Test> testsList = testRepository.findByTitle(input);
        Test testById = testRepository.findById(UUID.fromString(input)).get();
        testsList.add(testById);
        return new HashSet<>(testsList);
    }
}
