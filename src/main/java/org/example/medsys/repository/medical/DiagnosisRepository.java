package org.example.medsys.repository.medical;
import jakarta.validation.constraints.NotBlank;
import org.example.medsys.dto.medical.reports.DiagnosisCountResponse;
import org.example.medsys.entity.medical.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
	boolean existsByName(String name);

    @Query("""
        SELECT new org.example.medsys.dto.medical.reports.DiagnosisCountResponse(d.id,d.name,count(v))
        FROM Diagnosis d left join d.visits v
        group by d.id, d.name
        order by count(v) desc, d.name
""")
    List<DiagnosisCountResponse> countAllUsedDiagnoses();
}
