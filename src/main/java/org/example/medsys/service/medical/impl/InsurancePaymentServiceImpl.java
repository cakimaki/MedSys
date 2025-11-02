package org.example.medsys.service.medical.impl;


import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentRequest;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentResponse;
import org.example.medsys.entity.medical.InsurancePayment;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.repository.medical.InsurancePaymentRepository;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.service.medical.InsurancePaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InsurancePaymentServiceImpl implements InsurancePaymentService {
    private final PatientRepository patientRepository;
	private final InsurancePaymentRepository insurancePaymentRepository;
	
	public InsurancePaymentServiceImpl(PatientRepository patientRepository, InsurancePaymentRepository insurancePaymentRepository) {
        this.patientRepository = patientRepository;

        this.insurancePaymentRepository = insurancePaymentRepository;
	}

    @Transactional
    @Override
    public InsurancePaymentResponse createInsurancePayment(InsurancePaymentRequest request){
        InsurancePayment payment = new InsurancePayment();
        Patient patient = patientRepository.findById(request.getPatient_id())
                        .orElseThrow(()-> new NoSuchElementException("No patient found wiht id" + request.getPatient_id()));

        payment.setPatient(patient);
        payment.setMonth(request.getMonth());
        payment.setYear(request.getYear());
        payment.setPaid(payment.isPaid());

        insurancePaymentRepository.save(payment);
        return toResponse(payment);
	}

    @Transactional(readOnly = true)
    @Override
    public List<InsurancePaymentResponse> getInsurancePaymentsForPatientId(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new NoSuchElementException("No patient found wiht id" + patientId));

        return insurancePaymentRepository.findAllByPatient(patient)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public InsurancePaymentResponse changeToPaid(Long paymentId){
        InsurancePayment payment = insurancePaymentRepository.findById(paymentId)
                .orElseThrow(()-> new NoSuchElementException("No payment found with id:" + paymentId));

        payment.setPaid(true);

        return toResponse(payment);
    }

    @Transactional
    public InsurancePaymentResponse changeToNotPaid(Long paymentId){
        InsurancePayment payment = insurancePaymentRepository.findById(paymentId)
                .orElseThrow(()-> new NoSuchElementException("No payment found with id:" + paymentId));

        payment.setPaid(false);

        return toResponse(payment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InsurancePaymentResponse> getInsurancePaymentsForPatientIdForYear(Long patientId, Integer year){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new NoSuchElementException("No patient found wiht id" + patientId));

        return insurancePaymentRepository.findAllByPatientAndYear(patient, year)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InsurancePaymentResponse toResponse(InsurancePayment payment) {
        return new InsurancePaymentResponse(
                payment.getId(),
                payment.getMonth(),
                payment.getYear(),
                payment.isPaid()
        );
    }

}
