package org.example.medsys.controller;

import org.example.medsys.dto.VisitDto;
import org.example.medsys.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {
	
	private final VisitService visitService;
	
	@Autowired
	public PatientController(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@GetMapping("/visits")
	public ResponseEntity<List<VisitDto>> getMyVisits(Authentication authentication) {
		// Get the authenticated patient's username (email)
		String email = authentication.getName();
		
		// Fetch visits specific to this patient
		List<VisitDto> visits = visitService.getVisitsByPatientEmail(email);
		return ResponseEntity.ok(visits);
	}
}