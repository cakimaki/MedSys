package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
	List<Visit> findByPatientId(Long patientId);
	
	List<Visit> findAllByPatientId(Long patientId);
	
	List<Visit> findAllByDoctorId(Long doctorId);
}