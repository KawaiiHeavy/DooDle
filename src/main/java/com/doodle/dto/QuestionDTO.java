package com.doodle.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public enum QuestionDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface QuestionText { @NotBlank String getQuestionText(); }
    private interface ScoreWeight { @NotBlank Double getScoreWeight(); }
    private interface ImageUrl { String getImageUrl(); }

    @Value
    public static class Create implements QuestionText, ScoreWeight, ImageUrl {
        String questionText;
        Double scoreWeight;
        String imageUrl;
    }

    @Value
    public static class Read implements Id, QuestionText, ScoreWeight, ImageUrl {
        UUID id;
        String questionText;
        Double scoreWeight;
        String imageUrl;
    }
}
