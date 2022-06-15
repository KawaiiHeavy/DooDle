package com.doodle.services;

import com.doodle.models.*;
import com.doodle.repostitories.AnswerRepository;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.repostitories.TestRepository;
import com.doodle.repostitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResultService resultService;

    public Test createTest(TestInput testInput){
        Test test = new Test();
        test.setCreator(userRepository.findById(testInput.getCreatorId()).get());
        test.setTitle(testInput.getTitle());
        test.setMaxBall(testInput.getMaxBall());
        test.setSeconds(testInput.getSeconds());
        return testRepository.save(test);
    }

    public Test saveTest(Test test){
        double maxBall = 0;
        for (Question question: test.getQuestions()){
            maxBall += question.getScoreWeight();
            for (Answer answer: question.getPossibleAnswers()){
                answer.setQuestion(question);
            }
        }
        test.setMaxBall(maxBall);
        return this.testRepository.save(test);
    }

    public Set<Test> getTests(){
//        Set<Test> rawData = StreamSupport.stream(testRepository.findAll().spliterator(), false)
//                .collect(Collectors.toSet());
//        Stream<Test> stream = rawData.stream();
//        rawData.forEach(test -> {
//            test.setMembers(null);
//            test.setQuestions(null);
//            test.setResults(null);
//        });
//        System.out.println(rawData);
        return new HashSet<Test>((Collection<? extends Test>) testRepository.findAll());
    }

    public Test createTest(Test test){
        return testRepository.save(test);
    }

    public Set<Test> findTests(String input){
        Set<Test> testsList = testRepository.findByTitle(input);
        if (!testsList.isEmpty()){
            return testsList;
        }
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

    public Result checkTest(TestBlank testBlank){

        System.out.println(testBlank);

        double score = resultService.calculateScoreForTest(testBlank);

        Result result = new Result();
        result.setId(UUID.randomUUID());
        result.setScore(score);
        Test test = testRepository.findById(testBlank.getId()).get();
        result.saveTest(test);
        result.setParticipant(testBlank.getParticipant());
        saveTest(test);

        return result;
    }

}
