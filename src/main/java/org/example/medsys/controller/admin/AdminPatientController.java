package org.example.medsys.controller.admin;

import org.example.medsys.dto.auth.PatientCreationRequest;
import org.example.medsys.dto.auth.PatientCreationResponse;
import org.example.medsys.service.medical.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminPatientController {
	
	private final PatientService patientService;
	
	@Autowired
	public AdminPatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/patient/create")
	public ResponseEntity<PatientCreationResponse> createPatient(@RequestBody PatientCreationRequest dto) {
		try{
			PatientCreationResponse patient = patientService.createPatient(dto);
			return ResponseEntity.ok(patient);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
