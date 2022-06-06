package com.doodle.services;

import com.doodle.models.Answer;
import com.doodle.models.QuestionBlank;
import com.doodle.models.Result;
import com.doodle.models.TestBlank;
import com.doodle.repostitories.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public double calculateScoreForTest(TestBlank testBlank){
        double score = 0;

        for (QuestionBlank questionBlank: testBlank.getQuestionBlanks()){
            List<Answer> answers = questionBlank.getAnswers();
            Integer countOfRightAnswers = getCountOfRightAnswers(answers);
            List<Answer> userAnswers = questionBlank.getUserAnswers();

            System.out.println(questionBlank.getScoreWeight());
            System.out.println(countOfRightAnswers);

            score += calculateScoreForQuestion(answers, userAnswers, questionBlank.getScoreWeight());
        }

        return score;
    }

    public double calculateScoreForQuestion(List<Answer> answers, List<Answer> userAnswers, double scoreWeight){
        double score = 0;
        for (int i = 0; i < answers.size(); i++){
            if (answers.get(i).getCorrect() == userAnswers.get(i).getCorrect()){
                score += (scoreWeight / answers.size());
            }
        }
        return score;
    }

    public Integer getCountOfRightAnswers(List<Answer> answers){
        int count = 0;
        for (Answer answer: answers){
            if (answer.getCorrect()){
                count += 1;
            }
        }
        return count;
    }

    public Result save(Result result){
        return resultRepository.save(result);
    }
}
