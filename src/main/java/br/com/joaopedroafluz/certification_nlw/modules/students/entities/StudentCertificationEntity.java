package br.com.joaopedroafluz.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student_certifications")
public class StudentCertificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(length = 100)
    private String technology;

    @Column(length = 10)
    private int grade;

    @OneToMany
    @JoinColumn(name = "answer_certification_id")
    private List<AnswerCertificationEntity> answerCertificationEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

