package org.example.medsys.repository.medical;
import jakarta.validation.constraints.NotBlank;
import org.example.medsys.entity.medical.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
	boolean existsByName(String name);
}
