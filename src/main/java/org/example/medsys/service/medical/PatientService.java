package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.patient.PatientCreationRequest;
import org.example.medsys.dto.medical.patient.PatientCreationResponse;
import org.example.medsys.dto.medical.patient.PatientResponse;

import java.util.List;

public interface PatientService {
	
	PatientCreationResponse createPatient(PatientCreationRequest dto);
	
	PatientResponse getPatientById(Long id);
	
	@Transactional
	PatientResponse getPatientByEgn(String egn);
	
	List<PatientResponse> getAllPatients();
	
	List<PatientResponse> getAllPatientsByGpId(Long gpId);
	
	@Transactional
	void deletePatient(Long id);
}
