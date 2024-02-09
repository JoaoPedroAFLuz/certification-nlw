package br.com.joaopedroafluz.certification_nlw.modules.students.useCases;

import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.repositories.StudentCertificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerifyIfHasCertificationUseCase {

    private final StudentCertificationRepository studentCertificationRepository;

    public boolean execute(VerifyHasCertificationDTO dto) {
        final var studentCertification = studentCertificationRepository
                .findByEmailAndTechnology(dto.getEmail(), dto.getTechnology());

        return !studentCertification.isEmpty();
    }

}
