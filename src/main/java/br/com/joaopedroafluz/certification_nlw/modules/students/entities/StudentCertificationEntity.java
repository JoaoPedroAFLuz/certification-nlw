package br.com.joaopedroafluz.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
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
    @JsonManagedReference
    private StudentEntity studentEntity;

    @Column(length = 100)
    private String technology;

    @Column(length = 10)
    private Double grade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification_id")
    @JsonManagedReference
    private List<AnswerCertificationEntity> answerCertificationEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

