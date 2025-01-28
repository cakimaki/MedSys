package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.DiagnosisRequest;
import org.example.medsys.dto.DiagnosisResponse;

public interface DiagnosisService {
	@Transactional
	DiagnosisResponse createDiagnosis(DiagnosisRequest request);
	
	DiagnosisResponse getDiagnosisById(Long id);
	
	@Transactional
	void deleteDiagnosis(Long id);
}
