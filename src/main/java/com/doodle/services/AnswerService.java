package com.doodle.services;

import com.doodle.dto.AnswerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface AnswerService {

    Set<AnswerDTO.Read> getAllAnswers();
    Page<AnswerDTO.Read> getAllAnswersPageable(Pageable pageable);
    AnswerDTO.Read createAnswer(AnswerDTO.Create answerDTO);
    AnswerDTO.Read findAnswerById(UUID id);
    AnswerDTO.Read updateAnswer(AnswerDTO.Read answerDTO);
    void deleteAnswer(UUID id);
    
}
