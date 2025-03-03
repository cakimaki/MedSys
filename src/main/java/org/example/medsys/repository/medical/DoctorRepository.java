package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Query("SELECT d FROM Doctor d JOIN FETCH d.specializations")
	List<Doctor> findAllWithSpecializations();
	
	Optional<Doctor> findByUser_Egn(String doctorEgn);
}