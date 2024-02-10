package br.com.joaopedroafluz.certification_nlw.modules.certifications.controllers;

import br.com.joaopedroafluz.certification_nlw.modules.certifications.useCases.Top10RankingUseCase;
import br.com.joaopedroafluz.certification_nlw.modules.students.entities.StudentCertificationEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@AllArgsConstructor
public class RankingController {

    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top-10/{technology}")
    public List<StudentCertificationEntity> top10(@PathVariable String technology) {
        return top10RankingUseCase.execute(technology);
    }

}
