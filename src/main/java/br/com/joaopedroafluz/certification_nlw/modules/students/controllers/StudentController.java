package br.com.joaopedroafluz.certification_nlw.modules.students.controllers;

import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("/verify-if-has-certification")
    public boolean verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        return verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
    }

}
