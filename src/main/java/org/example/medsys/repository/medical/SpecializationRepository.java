package org.example.medsys.repository.medical;
import jakarta.validation.constraints.NotBlank;
import org.example.medsys.entity.medical.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
	boolean existsByName (String name);
}
