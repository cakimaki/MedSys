package org.example.medsys.controller.doctor;

import org.example.medsys.dto.medical.diagnosis.DiagnosisResponse;
import org.example.medsys.service.medical.DiagnosisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor/diagnosis")
public class DoctorDiagnosisController {
	private final DiagnosisService diagnosisService;
	
	public DoctorDiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	@GetMapping()
	public ResponseEntity<List<DiagnosisResponse>> getAllDiagnosis(){
		List<DiagnosisResponse> responses = diagnosisService.getAllDiagnosis();
		return ResponseEntity.ok(responses);
	}
}
