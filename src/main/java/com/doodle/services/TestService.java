package com.doodle.services;

import com.doodle.models.Question;
import com.doodle.models.Test;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.repostitories.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestService {

    private TestRepository testRepository;

    private QuestionRepository questionRepository;

    public Test createTest(Test test){
        return testRepository.save(test);
    }

    public Set<Test> findTests(String input){
        List<Test> testsList = testRepository.findByTitle(input);
        Test testById = testRepository.findById(UUID.fromString(input)).get();
        testsList.add(testById);
        return new HashSet<>(testsList);
    }

    public Set<Question> createQuestions(Set<Question> questions){
        return new HashSet<>(questionRepository.saveAll(questions));
    }
}
