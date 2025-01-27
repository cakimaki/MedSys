package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
	//Optional<Doctor> findById(Long id);
}
