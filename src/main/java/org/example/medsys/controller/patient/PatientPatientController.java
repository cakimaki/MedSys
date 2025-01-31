package org.example.medsys.controller.patient;

import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.dto.medical.visit.VisitDto;
import org.example.medsys.service.medical.PatientService;
import org.example.medsys.service.medical.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientPatientController {
	
	private final VisitService visitService;
	private final PatientService patientService;
	
	@Autowired
	public PatientPatientController(VisitService visitService, PatientService patientService) {
		this.visitService = visitService;
		this.patientService = patientService;
	}
	
	@GetMapping
	public ResponseEntity<PatientResponse> getPatient(Authentication authentication){
		String egn = authentication.getName();
		PatientResponse response = patientService.getPatientByEgn(egn);
		return ResponseEntity.ok(response);
	}
}