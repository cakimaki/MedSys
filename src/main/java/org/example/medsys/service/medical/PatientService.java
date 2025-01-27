package org.example.medsys.service.medical;

import org.example.medsys.dto.medical.patient.PatientCreationRequest;
import org.example.medsys.dto.medical.patient.PatientCreationResponse;

public interface PatientService {
	
	PatientCreationResponse createPatient(PatientCreationRequest dto);
}
