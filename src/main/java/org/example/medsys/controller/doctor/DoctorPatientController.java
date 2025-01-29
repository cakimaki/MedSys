package org.example.medsys.controller.doctor;

import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.service.medical.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/patient")
public class DoctorPatientController {
	private final PatientService patientService;
	
	public DoctorPatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping("/byEgn/{egn}")
	public ResponseEntity<PatientResponse> searchPatientByEgn(@PathVariable String egn){
		PatientResponse response = patientService.getPatientByEgn(egn);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PatientResponse>> getAllPatients() {
		List<PatientResponse> responses = patientService.getAllPatients();
		return ResponseEntity.ok(responses);
	}
}
