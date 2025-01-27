package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.specialization.SpecializationRequest;
import org.example.medsys.dto.medical.specialization.SpecializationResponse;
import org.example.medsys.entity.medical.Specialization;
import org.example.medsys.repository.medical.SpecializationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecializationServiceImpl implements SpecializationService{
	private final SpecializationRepository specializationRepository;
	private final ModelMapper modelMapper;
	
	public SpecializationServiceImpl(SpecializationRepository specializationRepository, ModelMapper modelMapper) {
		this.specializationRepository = specializationRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	@Transactional
	public SpecializationResponse createSpecialization(SpecializationRequest request){
		if(specializationRepository.existsByName(request.getName())){
			throw new IllegalArgumentException("Specialization already exists.");
		}
		
		//Create and save the specialization
		Specialization specialization = new Specialization();
		specialization.setName(request.getName());
		Specialization savedSpecialization = specializationRepository.save(specialization);
		
		//map to DTO
		return modelMapper.map(savedSpecialization,SpecializationResponse.class);
	}
	
	@Override
	public SpecializationResponse fetchSpecializationById(long id){
		Specialization specialization = specializationRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Specialization not found by id " + id +"."));
		return modelMapper.map(specialization, SpecializationResponse.class);
	}
	
	@Override
	public List<SpecializationResponse> fetchAllSpecializations(){
		List<Specialization> specializations = specializationRepository.findAll();
		
		return specializations.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponse.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public void deleteSpecialization(long id){
		Specialization specialization = specializationRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Specialization not found."));
		
		specializationRepository.delete(specialization);
	}
}



