package br.com.joaopedroafluz.certification_nlw.modules.questions.useCases;

import br.com.joaopedroafluz.certification_nlw.modules.questions.module.QuestionEntity;
import br.com.joaopedroafluz.certification_nlw.modules.questions.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionUseCase {

    private final QuestionRepository questionRepository;

    public List<QuestionEntity> findByTechnology(String technology) {
        return questionRepository.findByTechnology(technology);
    }

}
