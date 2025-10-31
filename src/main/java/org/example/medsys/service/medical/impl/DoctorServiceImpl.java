package org.example.medsys.service.medical.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Specialization;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.SpecializationRepository;
import org.example.medsys.service.auth.AppUserService;
import org.example.medsys.service.medical.DoctorService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
	private final DoctorRepository doctorRepository;
	private final SpecializationRepository specializationRepository;
	private final AppUserService appUserService;
	
	
	@Override
	@Transactional
	public DoctorResponse createDoctor(DoctorRequest request) {
		// Create AppUser with doctor role
		AppUser appUser = appUserService.createAppUser(
				request.getEgn(),
				request.getPassword(),
				Collections.singletonList("DOCTOR"));
		
		// Fetch Specializations
		List<Specialization> specializations = specializationRepository.findAllById(request.getSpecializationIds());
		if (specializations.size() != request.getSpecializationIds().size()) {
			throw new IllegalArgumentException("One or more specializations not found.");
		}
		
		// Create Doctor
		Doctor doctor = new Doctor();
		doctor.setUser(appUser);
		doctor.setName(request.getName());
		doctor.setSpecializations(specializations);
		
		Doctor savedDoctor = doctorRepository.save(doctor);
		
		// Manual mapping to DoctorResponse
		return mapToDoctorResponse(savedDoctor);
	}
	
	@Override
	@Transactional
	public DoctorResponse fetchDoctorById(long id) {
		// Fetch Doctor by ID
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + id));
		
		// Manual mapping to DoctorResponse
		return mapToDoctorResponse(doctor);
	}
	
	@Override
	@Transactional
	public List<DoctorResponse> fetchAllDoctors() {
		// Fetch all doctors with specializations eagerly
		List<Doctor> doctors = doctorRepository.findAllWithSpecializations();
		
		// Log the fetched doctors for debugging
		doctors.forEach(doctor -> {
			System.out.println("Doctor: " + doctor.getName());
			System.out.println("Specializations: " + doctor.getSpecializations());
		});
		
		// Manual mapping to DoctorResponse list
		return doctors.stream()
				.map(this::mapToDoctorResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void deleteDoctor(long id) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + id));
		
		// Delete the doctor
		doctorRepository.delete(doctor);
	}
	
	private DoctorResponse mapToDoctorResponse(Doctor doctor) {
		DoctorResponse response = new DoctorResponse();
		response.setId(doctor.getId());
		response.setName(doctor.getName());
		response.setEgn(doctor.getUser().getEgn());
		
		// Map specializations to their names
		List<String> specializationNames = doctor.getSpecializations().stream()
				.map(Specialization::getName)
				.collect(Collectors.toList());
		response.setSpecializations(specializationNames);
		
		return response;
	}
}