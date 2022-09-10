package com.doodle.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public enum AnswerDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface AnswerText { @NotBlank String getAnswerText(); }
    private interface Correct { @NotBlank Boolean getCorrect(); }

    @Data
    @AllArgsConstructor
    public static class Create implements AnswerText, Correct{
        String answerText;
        Boolean correct;
    }

    @Data
    @AllArgsConstructor
    public static class Read implements Id, AnswerText, Correct {
        UUID id;
        String answerText;
        Boolean correct;
    }

}
