package org.example.medsys.repository.medical;
import org.example.medsys.dto.medical.reports.CountPatientsPerGpResponse;
import org.example.medsys.dto.medical.reports.CountVisitsPerGpResponse;
import org.example.medsys.entity.medical.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

	List<Patient> findAllByGpId(Long gpId);
	
	Optional<Patient> findByUser_Egn(String egn);

    List<Patient> findDistinctByVisits_Diagnoses_Name(String diagnosis);

    List<Patient> findByGp_IdOrderByNameAsc(Long gpId);

    @Query("""
        select new org.example.medsys.dto.medical.reports.CountPatientsPerGpResponse(
            p.gp.id, p.gp.name, count(p)
        )
        FROM Patient p
        group by p.gp.id, p.gp.name
        order by count(p) desc, p.gp.name
""")
    List<CountPatientsPerGpResponse> countPatientsPerGp();

}
