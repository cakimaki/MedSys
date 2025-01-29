package org.example.medsys.controller.doctor;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.medsys.dto.medical.visit.VisitRequest;
import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.service.medical.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor/visit")
public class DoctorVisitController {
	private final VisitService visitService;
	
	public DoctorVisitController(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@PostMapping
	public ResponseEntity<VisitResponse> createVisit(@Valid @RequestBody VisitRequest request, Authentication authentication){
		String doctorEgn = authentication.getName();
		VisitResponse response = visitService.createVisitForDoctor(request,doctorEgn);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
