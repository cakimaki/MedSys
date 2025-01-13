package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
	Optional<Patient> findByUserId(Long userId);
	
	@Query("SELECT p FROM Patient p WHERE p.user.egn = :egn")
	Optional<Patient> findByUserEgn(@Param("egn") String egn);
}
