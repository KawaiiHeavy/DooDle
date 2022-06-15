package com.doodle.services;

import com.doodle.models.Question;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.repostitories.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question save(Question question){
        return questionRepository.save(question);
    }

}
