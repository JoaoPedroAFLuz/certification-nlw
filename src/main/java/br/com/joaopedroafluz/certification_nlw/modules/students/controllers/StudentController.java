package br.com.joaopedroafluz.certification_nlw.modules.students.controllers;

import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.StudentCertificationAnswerDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import br.com.joaopedroafluz.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verify-if-has-certification")
    public boolean verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        return verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws Exception {
        try {
            final var certification = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);

            return ResponseEntity.ok().body(certification);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
