package com.doodle.dto;


import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public enum AnswerDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface AnswerText { @NotBlank String getAnswerText(); }
    private interface Correct { @NotBlank Boolean getCorrect(); }

    @Value
    public static class Create implements AnswerText, Correct {
        String answerText;
        Boolean correct;
    }

    @Value
    public static class Read implements Id, AnswerText, Correct {
        UUID id;
        String answerText;
        Boolean correct;
    }

}
