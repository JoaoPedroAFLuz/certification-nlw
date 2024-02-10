package br.com.joaopedroafluz.certification_nlw.modules.students.useCases;

import br.com.joaopedroafluz.certification_nlw.modules.questions.module.AlternativeEntity;
import br.com.joaopedroafluz.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.StudentCertificationAnswerDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import br.com.joaopedroafluz.certification_nlw.modules.students.entities.AnswerCertificationEntity;
import br.com.joaopedroafluz.certification_nlw.modules.students.entities.StudentCertificationEntity;
import br.com.joaopedroafluz.certification_nlw.modules.students.entities.StudentEntity;
import br.com.joaopedroafluz.certification_nlw.modules.students.repositories.StudentCertificationRepository;
import br.com.joaopedroafluz.certification_nlw.modules.students.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class StudentCertificationAnswersUseCase {

    private final VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;
    private final StudentCertificationRepository studentCertificationRepository;

    public StudentCertificationEntity execute(StudentCertificationAnswerDTO dto) throws Exception {
        final var studentHasCertificationForTheTechnology = verifyIfHasCertificationUseCase
                .execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (studentHasCertificationForTheTechnology) {
            throw new Exception("Estudante já possui certificação para esta tecnologia");
        }

        final var questionsByTechnology = questionRepository.findByTechnology(dto.getTechnology());

        final var answersCertifications = new ArrayList<AnswerCertificationEntity>();

        AtomicInteger correctAnswers = new AtomicInteger();

        dto.getQuestionsAnswers()
                .forEach(questionAnswer -> {
                    final var question = questionsByTechnology.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionId()))
                            .findFirst()
                            .orElseThrow();

                    final var correctAlternative = question.getAlternatives().stream()
                            .filter(AlternativeEntity::isCorrect)
                            .findFirst()
                            .orElseThrow();

                    final var isAnswerCorrect = questionAnswer.getAlternativeId().equals(correctAlternative.getId());

                    if (isAnswerCorrect) {
                        correctAnswers.incrementAndGet();
                    }

                    questionAnswer.setCorrect(isAnswerCorrect);

                    final var answerCertification = AnswerCertificationEntity.builder()
                            .questionId(questionAnswer.getQuestionId())
                            .answerID(questionAnswer.getAlternativeId())
                            .isCorrect(questionAnswer.isCorrect())
                            .build();

                    answersCertifications.add(answerCertification);
                });

        final var grade = getGrade(correctAnswers.get(), dto.getQuestionsAnswers().size());

        Double MINIMUM_GRADE = 7.0;
        if (grade < MINIMUM_GRADE) {
            throw new Exception("Estudante não atingiu a nota mínima de " + MINIMUM_GRADE + ". Nota obtida: " + grade);
        }

        final var student = studentRepository.findByEmail(dto.getEmail());

        UUID studentId;
        if (student.isEmpty()) {
            final var newStudent = StudentEntity.builder().email(dto.getEmail()).build();
            final var createdStudent = studentRepository.save(newStudent);
            studentId = createdStudent.getId();
        } else {
            studentId = student.get().getId();
        }

        final var studentCertification = StudentCertificationEntity.builder()
                .studentId(studentId)
                .technology(dto.getTechnology())
                .grade(grade)
                .build();

        studentCertificationRepository.save(studentCertification);

        answersCertifications.forEach(answerCertification -> {
            answerCertification.setStudentCertification(studentCertification);
            answerCertification.setStudentCertificationId(studentCertification.getId());
            answerCertification.setStudentId(studentId);
        });

        studentCertification.setAnswerCertificationEntities(answersCertifications);

        return studentCertificationRepository.save(studentCertification);
    }

    public Double getGrade(Integer amountCorrectAnswers, Integer amountQuestions) {
        double correctPercentage  = (double) amountCorrectAnswers / amountQuestions;
        double grade = correctPercentage  * 10;

        grade = Math.round(grade * 100.0) / 100.0;

        return grade;
    }

}
