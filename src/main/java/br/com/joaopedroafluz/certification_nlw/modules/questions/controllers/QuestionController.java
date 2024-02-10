package br.com.joaopedroafluz.certification_nlw.modules.questions.controllers;

import br.com.joaopedroafluz.certification_nlw.modules.questions.dtos.AlternativesResultDTO;
import br.com.joaopedroafluz.certification_nlw.modules.questions.dtos.QuestionResultDTO;
import br.com.joaopedroafluz.certification_nlw.modules.questions.module.AlternativeEntity;
import br.com.joaopedroafluz.certification_nlw.modules.questions.module.QuestionEntity;
import br.com.joaopedroafluz.certification_nlw.modules.questions.useCases.QuestionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionUseCase questionUseCase;

    @GetMapping("/technologies/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        final var questions = questionUseCase.findByTechnology(technology);

        return mapQuestionToDTO(questions);
    }

    static List<QuestionResultDTO> mapQuestionToDTO(List<QuestionEntity> questions) {
        return questions.stream()
                .map(question -> QuestionResultDTO.builder()
                        .id(question.getId())
                        .description(question.getDescription())
                        .technology(question.getTechnology())
                        .alternatives(mapAlternativeToDTO(question.getAlternatives()))
                        .build())
                .collect(Collectors.toList());
    }

    static List<AlternativesResultDTO> mapAlternativeToDTO(List<AlternativeEntity> alternatives) {
        return alternatives.stream()
                .map(alternative -> AlternativesResultDTO.builder()
                        .id(alternative.getId())
                        .description(alternative.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

}
