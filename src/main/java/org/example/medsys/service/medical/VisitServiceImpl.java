package org.example.medsys.service.medical;

import org.example.medsys.dto.medical.visit.VisitDto;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.entity.medical.Visit;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.repository.medical.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService{
	
	
	private final VisitRepository visitRepository;
	private final PatientRepository patientRepository;
	
	@Autowired
	public VisitServiceImpl(VisitRepository visitRepository, PatientRepository patientRepository) {
		this.visitRepository = visitRepository;
		this.patientRepository = patientRepository;
	}
	
	@Override
	public List<VisitDto> getVisitsByPatientEmail(String egn) {
		// Fetch the patient by email
		Patient patient = patientRepository.findByUserEgn(egn)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		
		// Fetch visits for this patient
		List<Visit> visits = visitRepository.findByPatientId(patient.getId());
		
		// Map entities to DTOs
		return visits.stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	private VisitDto mapToDto(Visit visit) {
		if (visit == null) {
			throw new IllegalArgumentException("Visit cannot be null");
		}
		
		return new VisitDto(
				visit.getId(),
				visit.getDateTime(),
				visit.getDoctor() != null ? visit.getDoctor().getName() : "Unknown Doctor",
				visit.getDiagnosis() != null ? visit.getDiagnosis().getName() : "No Diagnosis",
				visit.getSickLeave() != null ? visit.getSickLeave().getStartDate() : null,
				visit.getSickLeave() != null ? visit.getSickLeave().getDurationDays() : null
		);
	}


}
