package com.doodle.services.impl;

import com.doodle.dto.AnswerDTO;
import com.doodle.exceptions.AnswerNotFoundException;
import com.doodle.models.Answer;
import com.doodle.repostitories.AnswerRepository;
import com.doodle.services.AnswerService;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;
    private Mapper mapper;

    public AnswerDTO.Read createAnswer(AnswerDTO.Create answer) {
        return mapper.mapToReadAnswerDTO(
                answerRepository.save(
                        mapper.mapToAnswer(answer)
                )
        );
    }


    public AnswerDTO.Read findAnswerById(UUID id) {
        return mapper.mapToReadAnswerDTO(answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id " + id + " was not found")));
    }

    @Override
    public AnswerDTO.Read updateAnswer(AnswerDTO.Read answerDTO) {
        return mapper.mapToReadAnswerDTO(
                answerRepository.save(
                        mapper.mapToAnswer(answerDTO)
                )
        );
    }

    public Set<AnswerDTO.Read> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(mapper::mapToReadAnswerDTO)
                .collect(Collectors.toSet());
    }

    public Page<AnswerDTO.Read> getAllAnswersPageable(Pageable pageable) {
        Page<Answer> page = answerRepository.findAll(pageable);
        return page.map(mapper::mapToReadAnswerDTO);
    }

    @Transactional
    public void deleteAnswer(UUID id) {
        Answer answer = answerRepository.getById(id);
        answerRepository.delete(answer);
    }

}
