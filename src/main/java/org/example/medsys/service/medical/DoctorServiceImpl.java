package org.example.medsys.service.medical;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Specialization;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.SpecializationRepository;
import org.example.medsys.service.auth.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
	private final DoctorRepository doctorRepository;
	private final AppUserService appUserService;
	private final SpecializationRepository specializationRepository;
	private final ModelMapper modelMapper;
	
	public DoctorServiceImpl(DoctorRepository doctorRepository, AppUserService appUserService, SpecializationRepository specializationRepository, ModelMapper modelMapper) {
		this.doctorRepository = doctorRepository;
		this.appUserService = appUserService;
		this.specializationRepository = specializationRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional
	@Override
	public DoctorResponse createDoctor(DoctorRequest request) {
		// Create AppUser with doctor role
		AppUser appUser = appUserService.createAppUser(
				request.getEgn(),
				request.getPassword(),
				Collections.singletonList("ROLE_DOCTOR"));
		
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
		
		return modelMapper.map(savedDoctor, DoctorResponse.class);
	}
	
	public void deleteDoctor(long id){
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Doc not found with id: " + id));
				
	}
}
