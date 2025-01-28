package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;

import java.util.List;

public interface DoctorService {
	@Transactional
	DoctorResponse createDoctor(DoctorRequest request);
	
	@Transactional
	DoctorResponse fetchDoctorById(long id);
	
	List<DoctorResponse> fetchAllDoctors();
	
	void deleteDoctor(long id);
}
