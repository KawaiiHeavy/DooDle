package com.doodle.services.impl;

import com.doodle.dto.QuestionDTO;
import com.doodle.exceptions.QuestionNotFoundException;
import com.doodle.models.Answer;
import com.doodle.models.ImageModel;
import com.doodle.models.Question;
import com.doodle.repostitories.ImageModelRepository;
import com.doodle.repostitories.QuestionRepository;
import com.doodle.services.QuestionService;
import com.doodle.utils.ByteCompressor;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private ImageModelRepository imageModelRepository;
    private Mapper mapper;
    private ByteCompressor compressor;

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

    @Transactional
    public void uploadImageToQuestion(UUID questionId, MultipartFile file) {
        ImageModel img = new ImageModel();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        byte[] picBytes = null;
        try {
            picBytes = compressor.compressBytes(file.getBytes());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        img.setPicByte(picBytes);
        img = imageModelRepository.saveAndFlush(img);
        questionRepository.addImageToQuestion(questionId, img);
    }

    public ImageModel getImageFromQuestion(UUID questionId) {
        final Optional<ImageModel> retrievedImage = questionRepository.getImageFromQuestion(questionId);
        ImageModel img = new ImageModel();
        img.setName(retrievedImage.get().getName());
        img.setType(retrievedImage.get().getType());
        img.setPicByte(compressor.decompressBytes(retrievedImage.get().getPicByte()));

        return img;
    }
}
