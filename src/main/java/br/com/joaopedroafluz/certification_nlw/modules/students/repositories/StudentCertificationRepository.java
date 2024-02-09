package br.com.joaopedroafluz.certification_nlw.modules.students.repositories;

import br.com.joaopedroafluz.certification_nlw.modules.students.entities.StudentCertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StudentCertificationRepository extends JpaRepository<StudentCertificationEntity, UUID> {

    @Query("SELECT sc FROM student_certifications sc "
            + "INNER JOIN sc.studentEntity std "
            + "WHERE std.email = :email "
            + "AND sc.technology = :technology ")
    List<StudentCertificationEntity> findByEmailAndTechnology(@Param("email") String email,
                                                              @Param("technology") String technology);

}
