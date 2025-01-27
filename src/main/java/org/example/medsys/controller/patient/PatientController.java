package org.example.medsys.controller.patient;

import org.example.medsys.dto.VisitDto;
import org.example.medsys.dto.auth.PatientCreationRequest;
import org.example.medsys.dto.auth.PatientCreationResponse;
import org.example.medsys.service.medical.PatientService;
import org.example.medsys.service.medical.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	private final VisitService visitService;
	private final PatientService patientService;
	
	@Autowired
	public PatientController(VisitService visitService, PatientService patientService) {
		this.visitService = visitService;
		this.patientService = patientService;
	}
	
	
	
	@GetMapping("/visits")
	public ResponseEntity<List<VisitDto>> getMyVisits(Authentication authentication) {
		// Get the authenticated patient's username (email)
		String email = authentication.getName();
		
		// Fetch visits specific to this patient
		List<VisitDto> visits = visitService.getVisitsByPatientEmail(email);
		return ResponseEntity.ok(visits);
	}
	
	//DOCTOR REQUESTS
	
	//PATIENT REQUESTS
	
}