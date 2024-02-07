package br.com.joaopedroafluz.certification_nlw.modules.students.useCases;

import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyHasCertificationDTO dto) {
        if (dto.getEmail().equals("joao.pedro.luz@hotmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }

        return false;
    }

}
