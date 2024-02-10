package br.com.joaopedroafluz.certification_nlw.modules.students.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class QuestionAnswerDTO {

    private UUID questionId;
    private UUID alternativeId;
    private boolean isCorrect;

}
