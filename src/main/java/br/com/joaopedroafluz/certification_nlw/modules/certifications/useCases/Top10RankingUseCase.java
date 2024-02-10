package br.com.joaopedroafluz.certification_nlw.modules.certifications.useCases;

import br.com.joaopedroafluz.certification_nlw.modules.students.entities.StudentCertificationEntity;
import br.com.joaopedroafluz.certification_nlw.modules.students.repositories.StudentCertificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Top10RankingUseCase {

    private StudentCertificationRepository studentCertificationRepository;

    public List<StudentCertificationEntity> execute(String technology) {
        return studentCertificationRepository.findTop10ByTechnologyOrderByGradeDesc(technology);
    }

}
