package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.DiagnosisRequest;
import org.example.medsys.dto.DiagnosisResponse;
import org.example.medsys.entity.medical.Diagnosis;
import org.example.medsys.repository.medical.DiagnosisRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
	
	private final DiagnosisRepository diagnosisRepository;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public DiagnosisResponse createDiagnosis(DiagnosisRequest request) {
		// Check if the diagnosis already exists
		if (diagnosisRepository.existsByName(request.getName())) {
			throw new IllegalArgumentException("Diagnosis with the same name already exists.");
		}
		
		// Create and save the diagnosis
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setName(request.getName());
		Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis);
		
		// Map to response DTO
		return modelMapper.map(savedDiagnosis, DiagnosisResponse.class);
	}
	
	@Override
	public DiagnosisResponse getDiagnosisById(Long id) {
		// Fetch diagnosis by ID
		Diagnosis diagnosis = diagnosisRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found."));
		return modelMapper.map(diagnosis, DiagnosisResponse.class);
	}
	
	@Transactional
	@Override
	public void deleteDiagnosis(Long id) {
		// Check if the diagnosis exists
		Diagnosis diagnosis = diagnosisRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found."));
		
		// Delete the diagnosis
		diagnosisRepository.delete(diagnosis);
	}
}