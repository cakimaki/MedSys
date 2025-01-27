package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.auth.PatientCreationRequest;
import org.example.medsys.dto.auth.PatientCreationResponse;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.service.auth.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository patientRepository;
	private final AppUserService appUserService;
	private final DoctorRepository doctorRepository;
	private final ModelMapper modelMapper;
	
	public PatientServiceImpl(PatientRepository patientRepository, AppUserService appUserService, DoctorRepository doctorRepository, ModelMapper modelMapper) {
		this.patientRepository = patientRepository;
		this.appUserService = appUserService;
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	@Transactional
	public PatientCreationResponse createPatient(PatientCreationRequest dto) {
		// Create AppUser
		AppUser appUser = appUserService.createAppUser(
				dto.getEgn(),
				dto.getPassword(),
				Collections.singletonList("ROLE_PATIENT")
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
		
		// Map Patient to PatientCreationResponse using ModelMapper
		return modelMapper.map(savedPatient, PatientCreationResponse.class);
	}
}
