package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
	List<Visit> findByPatientId(Long patientId);
	
	//if using the native query . -
	/*@Query("SELECT v FROM Visit v WHERE v.patient.id = :patientId")
	List<Visit> findByPatientId(@Param("patientId") Long patientId);*/
}