package org.example.medsys.service.medical;

import org.example.medsys.dto.medical.specialization.SpecializationRequest;
import org.example.medsys.dto.medical.specialization.SpecializationResponse;

import java.util.List;

public interface SpecializationService {
	SpecializationResponse createSpecialization(SpecializationRequest request);
	
	SpecializationResponse fetchSpecializationById(long id);
	
	List<SpecializationResponse> fetchAllSpecializations();
	
	void deleteSpecialization(long id);
}
