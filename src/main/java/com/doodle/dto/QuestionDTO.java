package com.doodle.dto;

import com.doodle.models.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public enum QuestionDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface QuestionText { @NotBlank String getQuestionText(); }
    private interface ScoreWeight { @NotBlank Double getScoreWeight(); }
    private interface Image { ImageModel getImage(); }

    private interface Answers { @NotBlank Set<AnswerDTO.Create> getAnswers(); }

    private interface UserAnswers { @NotBlank Set<AnswerDTO.Create> getUserAnswers(); }

    @Data
    @AllArgsConstructor
    public static class Create implements QuestionText, ScoreWeight, Image, Answers {
        String questionText;
        Double scoreWeight;
        ImageModel image;
        Set<AnswerDTO.Create> answers;
    }

    @Data
    @AllArgsConstructor
    public static class Read implements Id, QuestionText, ScoreWeight, Image, Answers {
        UUID id;
        String questionText;
        Double scoreWeight;
        ImageModel image;
        Set<AnswerDTO.Create> answers;
    }

    @Data
    @AllArgsConstructor
    public static class Passed implements Id, QuestionText, ScoreWeight, Image, Answers, UserAnswers {
        UUID id;
        String questionText;
        Double scoreWeight;
        ImageModel image;
        Set<AnswerDTO.Create> answers;
        Set<AnswerDTO.Create> userAnswers;
    }
}