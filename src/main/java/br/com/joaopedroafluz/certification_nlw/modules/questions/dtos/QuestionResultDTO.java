package br.com.joaopedroafluz.certification_nlw.modules.questions.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class QuestionResultDTO {

    private UUID id;
    private String technology;
    private String description;
    private List<AlternativesResultDTO> alternatives;


}
