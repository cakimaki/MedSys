package org.example.medsys.service.medical;

import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentRequest;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentResponse;
import org.example.medsys.entity.medical.InsurancePayment;
import org.example.medsys.repository.medical.InsurancePaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class InsurancePaymentServiceImpl implements InsurancePaymentService{
	private final InsurancePaymentRepository insurancePaymentRepository;
	
	public InsurancePaymentServiceImpl(InsurancePaymentRepository insurancePaymentRepository) {
		this.insurancePaymentRepository = insurancePaymentRepository;
	}
	
	public InsurancePaymentResponse createInsurancePayment(InsurancePaymentRequest request){
		return null;
	}
	
}
