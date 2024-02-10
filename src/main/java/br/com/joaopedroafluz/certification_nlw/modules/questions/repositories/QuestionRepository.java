package br.com.joaopedroafluz.certification_nlw.modules.questions.repositories;

import br.com.joaopedroafluz.certification_nlw.modules.questions.module.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

    List<QuestionEntity> findByTechnology(String technology);

}
