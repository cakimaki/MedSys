package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.diagnosis.DiagnosisRequest;
import org.example.medsys.dto.medical.diagnosis.DiagnosisResponse;

import java.util.List;

public interface DiagnosisService {
	@Transactional
	DiagnosisResponse createDiagnosis(DiagnosisRequest request);
	
	List<DiagnosisResponse> getAllDiagnosis();
	
	@Transactional
	DiagnosisResponse updateDiagnosis(Long id, DiagnosisRequest request);
	
	@Transactional
	void deleteDiagnosis(Long id);
}
