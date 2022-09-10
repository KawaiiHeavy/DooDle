package com.doodle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public enum QuestionDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface QuestionText { @NotBlank String getQuestionText(); }
    private interface ScoreWeight { @NotBlank Double getScoreWeight(); }
    private interface ImageUrl { String getImageUrl(); }

    private interface Answers { @NotBlank Set<AnswerDTO.Create> getAnswers(); }

    private interface UserAnswers { @NotBlank Set<AnswerDTO.Create> getUserAnswers(); }

    @Data
    @AllArgsConstructor
    public static class Create implements QuestionText, ScoreWeight, ImageUrl, Answers {
        String questionText;
        Double scoreWeight;
        String imageUrl;
        Set<AnswerDTO.Create> answers;
    }

    @Data
    @AllArgsConstructor
    public static class Read implements Id, QuestionText, ScoreWeight, ImageUrl, Answers {
        UUID id;
        String questionText;
        Double scoreWeight;
        String imageUrl;
        Set<AnswerDTO.Create> answers;
    }

    @Data
    @AllArgsConstructor
    public static class Passed implements Id, QuestionText, ScoreWeight, ImageUrl, Answers, UserAnswers {
        UUID id;
        String questionText;
        Double scoreWeight;
        String imageUrl;
        Set<AnswerDTO.Create> answers;
        Set<AnswerDTO.Create> userAnswers;
    }
}
