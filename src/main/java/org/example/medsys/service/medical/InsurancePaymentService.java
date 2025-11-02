package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentRequest;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentResponse;

import java.util.List;

public interface InsurancePaymentService  {

    @Transactional
    InsurancePaymentResponse createInsurancePayment(InsurancePaymentRequest request);

    List<InsurancePaymentResponse> getInsurancePaymentsForPatientId(Long patientId);

    List<InsurancePaymentResponse> getInsurancePaymentsForPatientIdForYear(Long patientId, Integer year);

    InsurancePaymentResponse changeToPaid(Long paymentId);

    InsurancePaymentResponse changeToNotPaid(Long paymentId);
}
