package org.example.medsys.service.medical.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.medical.diagnosis.DiagnosisRequest;
import org.example.medsys.dto.medical.diagnosis.DiagnosisResponse;
import org.example.medsys.entity.medical.Diagnosis;
import org.example.medsys.repository.medical.DiagnosisRepository;
import org.example.medsys.service.medical.DiagnosisService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
	
	private final DiagnosisRepository diagnosisRepository;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public DiagnosisResponse createDiagnosis(DiagnosisRequest request) {
		if (diagnosisRepository.existsByName(request.getName())) {
			throw new IllegalArgumentException("Diagnosis with name '" + request.getName() + "' already exists.");
		}
		
		// Create and save diagnosis
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setName(request.getName());
		Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis);
		
		// Map to response
		return mapToDiagnosisResponse(savedDiagnosis);
	}
	
	@Override
	public List<DiagnosisResponse> getAllDiagnosis() {
		List<Diagnosis> diagnoses = diagnosisRepository.findAll();
		return diagnoses.stream()
				.map(this::mapToDiagnosisResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public DiagnosisResponse updateDiagnosis(Long id, DiagnosisRequest request) {
		// Fetch existing diagnosis
		Diagnosis diagnosis = diagnosisRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with id: " + id));
		
		// Update name
		if (!diagnosis.getName().equals(request.getName())) {
			if (diagnosisRepository.existsByName(request.getName())) {
				throw new IllegalArgumentException("Diagnosis with name '" + request.getName() + "' already exists.");
			}
			diagnosis.setName(request.getName());
		}
		
		// Save updated diagnosis
		Diagnosis updatedDiagnosis = diagnosisRepository.save(diagnosis);
		
		// Map to response
		return mapToDiagnosisResponse(updatedDiagnosis);
	}
	
	@Override
	@Transactional
	public void deleteDiagnosis(Long id) {
		Diagnosis diagnosis = diagnosisRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Diagnosis not found with id: " + id));
		
		if (diagnosis.getVisits() != null && !diagnosis.getVisits().isEmpty()) {
			throw new IllegalStateException("Cannot delete diagnosis with associated visits.");
		}
		
		diagnosisRepository.delete(diagnosis);
	}
	
	private DiagnosisResponse mapToDiagnosisResponse(Diagnosis diagnosis) {
		DiagnosisResponse response = new DiagnosisResponse();
		response.setId(diagnosis.getId());
		response.setName(diagnosis.getName());
		return response;
	}
}