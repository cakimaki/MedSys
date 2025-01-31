package org.example.medsys.controller.patient;


import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.service.medical.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/visit")
public class PatientVisitController {
	private final VisitService visitService;
	
	public PatientVisitController(VisitService visitService){
		this.visitService = visitService;
	}
	
	public ResponseEntity<List<VisitResponse>> getVisitsOfPatient(Authentication authentication){
		String egn = authentication.getName();
		List<VisitResponse> responses = visitService.getVisitsByPatientEgn(egn);
		return ResponseEntity.ok(responses);
	}
}
