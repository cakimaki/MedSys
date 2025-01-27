package org.example.medsys.service.medical;

import org.example.medsys.dto.auth.PatientCreationRequest;
import org.example.medsys.dto.auth.PatientCreationResponse;

public interface PatientService {
	
	PatientCreationResponse createPatient(PatientCreationRequest dto);
}
