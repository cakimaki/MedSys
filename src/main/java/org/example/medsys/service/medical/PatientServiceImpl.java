package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.medical.patient.PatientCreationRequest;
import org.example.medsys.dto.medical.patient.PatientCreationResponse;
import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.service.auth.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository patientRepository;
	private final AppUserService appUserService;
	private final DoctorRepository doctorRepository;
	private final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public PatientCreationResponse createPatient(PatientCreationRequest dto) {
		// Create AppUser
		AppUser appUser = appUserService.createAppUser(
				dto.getEgn(),
				dto.getPassword(),
				Collections.singletonList("PATIENT")
		);
		
		// Fetch GP
		Doctor gp = doctorRepository.findById(dto.getGpId())
				.orElseThrow(() -> new IllegalArgumentException("GP not found."));
		
		// Create Patient
		Patient patient = new Patient();
		patient.setUser(appUser);
		patient.setName(dto.getName());
		patient.setGp(gp);
		patient.setInsured(dto.isInsured());
		
		Patient savedPatient = patientRepository.save(patient);
		
		// Manual mapping for the response
		PatientCreationResponse response = new PatientCreationResponse();
		response.setId(savedPatient.getId());
		response.setName(savedPatient.getName());
		response.setEgn(savedPatient.getUser().getEgn());
		response.setInsured(savedPatient.isInsured());
		
		return response;
	}
	
	@Override
	@Transactional
	public PatientResponse getPatientById(Long id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));
		return mapToPatientResponse(patient);
	}
	
	@Transactional
	@Override
	public PatientResponse getPatientByEgn(String egn) {
		Patient patient = patientRepository.findByUser_Egn(egn)
				.orElseThrow(() -> new IllegalArgumentException("Patient not found with EGN: " + egn));
		return mapToPatientResponse(patient);
	}
	
	@Override
	@Transactional
	public List<PatientResponse> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		return patients.stream()
				.map(this::mapToPatientResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<PatientResponse> getAllPatientsByGpId(Long gpId) {
		List<Patient> patients = patientRepository.findAllByGpId(gpId);
		return patients.stream()
				.map(this::mapToPatientResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void deletePatient(Long id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));
		patientRepository.delete(patient);
	}
	
	private PatientResponse mapToPatientResponse(Patient patient) {
		PatientResponse response = new PatientResponse();
		response.setId(patient.getId());
		response.setName(patient.getName());
		response.setEgn(patient.getUser().getEgn());
		response.setGpId(patient.getGp().getId());
		response.setGpName(patient.getGp().getName()); // Map GP's name
		response.setInsured(patient.isInsured());
		return response;
	}
}
