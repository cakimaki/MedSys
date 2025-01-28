package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.patient.PatientCreationRequest;
import org.example.medsys.dto.medical.patient.PatientCreationResponse;
import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.service.medical.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/patient")
public class AdminPatientController {
	
	private final PatientService patientService;
	
	public AdminPatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@PostMapping
	public ResponseEntity<PatientCreationResponse> createPatient(@Valid @RequestBody PatientCreationRequest request) {
		PatientCreationResponse response = patientService.createPatient(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
		PatientResponse response = patientService.getPatientById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PatientResponse>> getAllPatients() {
		List<PatientResponse> responses = patientService.getAllPatients();
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/gp/{gpId}")
	public ResponseEntity<List<PatientResponse>> getAllPatientsByGpId(@PathVariable Long gpId) {
		List<PatientResponse> responses = patientService.getAllPatientsByGpId(gpId);
		return ResponseEntity.ok(responses);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
}
