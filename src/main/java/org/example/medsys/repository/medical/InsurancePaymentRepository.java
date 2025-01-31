package org.example.medsys.repository.medical;

import org.example.medsys.entity.medical.InsurancePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePaymentRepository extends JpaRepository<InsurancePayment,Long> {
}
