package br.com.joaopedroafluz.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answer_certifications")
public class AnswerCertificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity student;

    @Column(name = "student_certification_id")
    private UUID studentCertificationId;

    @ManyToOne
    @JoinColumn(name = "student_certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private StudentCertificationEntity studentCertification;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer_id")
    private UUID answerID;

    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
