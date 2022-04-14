package com.doodle.services;

import com.doodle.models.Answer;
import com.doodle.models.Question;
import com.doodle.models.Test;
import com.doodle.repostitories.AnswerRepository;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.models.TestInput;
import com.doodle.repostitories.TestRepository;
import com.doodle.repostitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TestService {

    private TestRepository testRepository;

    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    private UserRepository userRepository;

    public Test createTest(TestInput testInput){
        Test test = new Test();
        test.setCreator(userRepository.findById(testInput.getCreatorId()).get());
        test.setTitle(testInput.getTitle());
        test.setMaxBall(testInput.getMaxBall());
        test.setSeconds(testInput.getSeconds());
        return testRepository.save(test);
    }

    public Test createTest(Test test){
        return testRepository.save(test);
    }

    public Set<Test> findTests(String input){
        List<Test> testsList = testRepository.findByTitle(input);
        try {
            UUID id = UUID.fromString(input);
            Test testById = testRepository.findById(id).get();
            testsList.add(testById);
        }
        catch (IllegalArgumentException ex){
            System.out.println("Given not an id: " + ex);
        }
        return new HashSet<>(testsList);
    }

    public Set<Question> createQuestions(Set<Question> questions){
        return new HashSet<>(questionRepository.saveAll(questions));
    }

    public void createAnswers(Set<Answer> answers, Question question){
        answerRepository.saveAll(answers);
        for (Answer answer : answers) {
            answerRepository.saveAnswerToQuestion(answer.getId(), question.getId());
        }
    }

    public void deleteTest(Test test){
        testRepository.delete(test);
    }
}
