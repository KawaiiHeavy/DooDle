package com.doodle.services.impl;

import com.doodle.dto.QuestionDTO;
import com.doodle.exceptions.QuestionNotFoundException;
import com.doodle.models.Answer;
import com.doodle.models.Question;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.services.QuestionService;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private Mapper mapper;

    public QuestionDTO.Read createQuestion(QuestionDTO.Create question) {
        return mapper.mapToReadQuestionDTO(
                questionRepository.save(
                        mapper.mapToQuestion(question)
                )
        );
    }

    @Override
    public QuestionDTO.Read findQuestionById(UUID id) {
        return mapper.mapToReadQuestionDTO(questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found")));
    }

    @Override
    public QuestionDTO.Read updateQuestion(QuestionDTO.Read questionDTO) {
        return mapper.mapToReadQuestionDTO(
                questionRepository.save(
                        mapper.mapToQuestion(questionDTO)
                )
        );
    }


    public Set<QuestionDTO.Read> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(mapper::mapToReadQuestionDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<QuestionDTO.Read> getAllQuestionsPageable(Pageable pageable) {
        Page<Question> page = questionRepository.findAll(pageable);
        return page.map(mapper::mapToReadQuestionDTO);
    }

    public void deleteQuestion(UUID id) {
        Question question = questionRepository.getById(id);
        questionRepository.delete(question);
    }

    public byte[] getImageFromQuestion(UUID questionId) {
        byte[] imageBytes = null;
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            try {
                imageBytes = question.get().getImage().getBytes(1,
                        (int) question.get().getImage().length());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return imageBytes;
    }
}
