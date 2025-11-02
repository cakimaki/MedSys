package org.example.medsys.repository.medical;

import org.example.medsys.entity.medical.InsurancePayment;
import org.example.medsys.entity.medical.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsurancePaymentRepository extends JpaRepository<InsurancePayment,Long> {
    List<InsurancePayment> findAllByPatient(Patient patient);

    List<InsurancePayment> findAllByPatientAndYear(Patient patient, Integer year);

}
