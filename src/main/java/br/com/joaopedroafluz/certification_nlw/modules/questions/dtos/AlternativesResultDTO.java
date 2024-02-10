package br.com.joaopedroafluz.certification_nlw.modules.questions.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AlternativesResultDTO {

    private UUID id;
    private String description;

}
