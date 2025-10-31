package org.example.medsys.service.medical.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.medical.visit.VisitRequest;
import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.entity.medical.Diagnosis;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.entity.medical.Visit;
import org.example.medsys.repository.medical.DiagnosisRepository;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.repository.medical.VisitRepository;
import org.example.medsys.service.medical.VisitService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {
	
	private final VisitRepository visitRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final DiagnosisRepository diagnosisRepository;
	
	@Transactional
	@Override
	public VisitResponse createVisit(VisitRequest request) {
		// Fetch Patient
		Patient patient = patientRepository.findById(request.getPatientId())
				.orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + request.getPatientId()));
		
		// Fetch Doctor
		Doctor doctor = doctorRepository.findById(request.getDoctorId())
				.orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + request.getDoctorId()));
		
		// Fetch Diagnosis (optional)
		Diagnosis diagnosis = null;
		if (request.getDiagnosisId() != null) {
			diagnosis = diagnosisRepository.findById(request.getDiagnosisId())
					.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with id: " + request.getDiagnosisId()));
		}
		
		// Create Visit
		return getVisitResponse(request, patient, doctor, diagnosis);
	}
	
	@Transactional
	@Override
	public VisitResponse createVisitForDoctor(VisitRequest request, String doctorEgn) {
		// Fetch Patient
		Patient patient = patientRepository.findById(request.getPatientId())
				.orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + request.getPatientId()));
		
		// Fetch Doctor using EGN
		Doctor doctor = doctorRepository.findByUser_Egn(doctorEgn)
				.orElseThrow(() -> new IllegalArgumentException("Doctor not found with EGN: " + doctorEgn));
		
		// Fetch Diagnosis (optional)
		Diagnosis diagnosis = null;
		if (request.getDiagnosisId() != null) {
			diagnosis = diagnosisRepository.findById(request.getDiagnosisId())
					.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with ID: " + request.getDiagnosisId()));
		}
		
		// Create Visit
		return getVisitResponse(request, patient, doctor, diagnosis);
	}
	
	private VisitResponse getVisitResponse(VisitRequest request, Patient patient, Doctor doctor, Diagnosis diagnosis) {
		Visit visit = new Visit();
		visit.setPatient(patient);
		visit.setDoctor(doctor);
		visit.setDiagnosis(diagnosis);
		if(request.getDateTime()==null){
			visit.setDateTime(LocalDateTime.now());
			
		}else{
			visit.setDateTime(request.getDateTime());
		}
		visit.setTreatment(request.getTreatment());
		visit.setNotes(request.getNotes());
		
		Visit savedVisit = visitRepository.save(visit);
		
		return mapToVisitResponse(savedVisit);
	}
	
	@Override
	public VisitResponse getVisitById(Long id) {
		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
		return mapToVisitResponse(visit);
	}
	
	@Override
	public List<VisitResponse> getVisitsByPatientId(Long patientId) {
		// Fetch visits by patient ID
		List<Visit> visits = visitRepository.findAllByPatientId(patientId);
		if (visits.isEmpty()) {
			throw new IllegalArgumentException("No visits found for patient with id: " + patientId);
		}
		
		// Map visits to responses
		return visits.stream()
				.map(this::mapToVisitResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<VisitResponse> getVisitsByPatientEgn(String patientEgn) {
		// Fetch visits by patient egn
		List<Visit> visits = visitRepository.findAllByPatient_User_Egn(patientEgn);
		if (visits.isEmpty()) {
			throw new IllegalArgumentException("No visits found for patient with egn: " + patientEgn);
		}
		
		// Map visits to responses
		return visits.stream()
				.map(this::mapToVisitResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<VisitResponse> getVisitsByDoctorId(Long doctorId) {
		// Fetch visits by doctor ID
		List<Visit> visits = visitRepository.findAllByDoctorId(doctorId);
		if (visits.isEmpty()) {
			throw new IllegalArgumentException("No visits found for doctor with id: " + doctorId);
		}
		
		// Map visits to responses
		return visits.stream()
				.map(this::mapToVisitResponse)
				.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public VisitResponse updateVisit(Long id, VisitRequest request) {
		// Fetch existing Visit
		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
		
		// Update fields
		if (!visit.getPatient().getId().equals(request.getPatientId())) {
			Patient patient = patientRepository.findById(request.getPatientId())
					.orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + request.getPatientId()));
			visit.setPatient(patient);
		}
		
		if (!visit.getDoctor().getId().equals(request.getDoctorId())) {
			Doctor doctor = doctorRepository.findById(request.getDoctorId())
					.orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + request.getDoctorId()));
			visit.setDoctor(doctor);
		}
		
		if (request.getDiagnosisId() != null &&
				(visit.getDiagnosis() == null || !visit.getDiagnosis().getId().equals(request.getDiagnosisId()))) {
			Diagnosis diagnosis = diagnosisRepository.findById(request.getDiagnosisId())
					.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with id: " + request.getDiagnosisId()));
			visit.setDiagnosis(diagnosis);
		}
		
		visit.setDateTime(request.getDateTime());
		visit.setTreatment(request.getTreatment());
		visit.setNotes(request.getNotes());
		
		Visit updatedVisit = visitRepository.save(visit);
		
		return mapToVisitResponse(updatedVisit);
	}
	
	@Transactional
	@Override
	public VisitResponse updateVisitForDoctor(Long id, VisitRequest request, String doctorEgn) {
		// Fetch the visit
		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with ID: " + id));
		
		// Check if the authenticated doctor owns this visit
		if (!visit.getDoctor().getUser().getEgn().equals(doctorEgn)) {
			throw new SecurityException("You do not have permission to update this visit.");
		}
		
		// Update optional fields
		if (request.getDiagnosisId() != null && (visit.getDiagnosis() == null ||
				!visit.getDiagnosis().getId().equals(request.getDiagnosisId()))) {
			Diagnosis diagnosis = diagnosisRepository.findById(request.getDiagnosisId())
					.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with ID: " + request.getDiagnosisId()));
			visit.setDiagnosis(diagnosis);
		}
		
		visit.setDateTime(request.getDateTime() != null ? request.getDateTime() : visit.getDateTime());
		visit.setTreatment(request.getTreatment());
		visit.setNotes(request.getNotes());
		
		Visit updatedVisit = visitRepository.save(visit);
		return mapToVisitResponse(updatedVisit);
	}
	
	@Transactional
	@Override
	public void deleteVisit(Long id) {
		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
		visitRepository.delete(visit);
	}
	
	@Transactional
	@Override
	public void deleteVisitForDoctor(Long id, String doctorEgn) {
		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with ID: " + id));
		
		// Check if the authenticated doctor owns this visit
		if (!visit.getDoctor().getUser().getEgn().equals(doctorEgn)) {
			throw new SecurityException("You do not have permission to delete this visit.");
		}
		
		visitRepository.delete(visit);
	}
	
	private VisitResponse mapToVisitResponse(Visit visit) {
		VisitResponse response = new VisitResponse();
		response.setId(visit.getId());
		response.setPatientName(visit.getPatient().getName());
		response.setDoctorName(visit.getDoctor().getName());
		response.setDiagnosisName(visit.getDiagnosis() != null ? visit.getDiagnosis().getName() : null);
		response.setDateTime(visit.getDateTime());
		response.setTreatment(visit.getTreatment());
		response.setNotes(visit.getNotes());
		
		// Map sick leave details if present
		if (visit.getSickLeave() != null) {
			response.setSickLeaveStartDate(visit.getSickLeave().getStartDate());
			response.setSickLeaveDurationDays(visit.getSickLeave().getDurationDays());
		}
		
		return response;
	}
}