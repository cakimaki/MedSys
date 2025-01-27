package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
}
