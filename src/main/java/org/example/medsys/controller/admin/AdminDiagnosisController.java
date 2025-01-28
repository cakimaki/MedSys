package org.example.medsys.controller.admin;


import jakarta.validation.Valid;
import org.example.medsys.dto.medical.diagnosis.DiagnosisRequest;
import org.example.medsys.dto.medical.diagnosis.DiagnosisResponse;
import org.example.medsys.service.medical.DiagnosisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/diagnosis")
public class AdminDiagnosisController {
	
	private final DiagnosisService diagnosisService;
	
	public AdminDiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	@PostMapping
	public ResponseEntity<DiagnosisResponse> createDiagnosis(@Valid @RequestBody DiagnosisRequest request) {
		DiagnosisResponse response = diagnosisService.createDiagnosis(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<DiagnosisResponse>> getAllDiagnosis() {
		List<DiagnosisResponse> responses = diagnosisService.getAllDiagnosis();
		return ResponseEntity.ok(responses);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DiagnosisResponse> updateDiagnosis(
			@PathVariable Long id,
			@Valid @RequestBody DiagnosisRequest request) {
		DiagnosisResponse response = diagnosisService.updateDiagnosis(id, request);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDiagnosis(@PathVariable Long id) {
		diagnosisService.deleteDiagnosis(id);
		return ResponseEntity.noContent().build();
	}
}