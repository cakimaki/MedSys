package org.example.medsys.controller.doctor;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.visit.VisitRequest;
import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.service.medical.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<VisitResponse> getVisitById(@PathVariable Long id){
		VisitResponse response = visitService.getVisitById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<List<VisitResponse>> getVisitsByPatientId(@PathVariable Long id ){
		List<VisitResponse> responses = visitService.getVisitsByPatientId(id);
		return ResponseEntity.ok(responses);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VisitResponse> updateVisit(
			@PathVariable Long id,
			@Valid @RequestBody VisitRequest request,
			Authentication authentication) {
		String doctorEgn = authentication.getName();
		VisitResponse response = visitService.updateVisitForDoctor(id, request, doctorEgn);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVisit(@PathVariable Long id, Authentication authentication) {
		String doctorEgn = authentication.getName();
		visitService.deleteVisitForDoctor(id, doctorEgn);
		return ResponseEntity.noContent().build();
	}
}
