package com.doodle.services;

import com.doodle.dto.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface QuestionService {

    Set<QuestionDTO.Read> getAllQuestions();
    Page<QuestionDTO.Read> getAllQuestionsPageable(Pageable pageable);
    QuestionDTO.Read createQuestion(QuestionDTO.Create questionDTO);
    QuestionDTO.Read findQuestionById(UUID id);
    QuestionDTO.Read updateQuestion(QuestionDTO.Read questionDTO);
    void deleteQuestion(UUID id);
    
}
