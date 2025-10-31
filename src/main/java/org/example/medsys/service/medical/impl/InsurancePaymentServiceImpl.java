package org.example.medsys.service.medical.impl;

import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentRequest;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentResponse;
import org.example.medsys.repository.medical.InsurancePaymentRepository;
import org.example.medsys.service.medical.InsurancePaymentService;
import org.springframework.stereotype.Service;

@Service
public class InsurancePaymentServiceImpl implements InsurancePaymentService {
	private final InsurancePaymentRepository insurancePaymentRepository;
	
	public InsurancePaymentServiceImpl(InsurancePaymentRepository insurancePaymentRepository) {
		this.insurancePaymentRepository = insurancePaymentRepository;
	}
	
	public InsurancePaymentResponse createInsurancePayment(InsurancePaymentRequest request){
		return null;
	}
	
}
