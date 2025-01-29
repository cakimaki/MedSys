package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.VisitRequest;
import org.example.medsys.dto.medical.VisitResponse;
import org.example.medsys.service.medical.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/visit")
public class AdminVisitController {
	
	private final VisitService visitService;
	
	public AdminVisitController(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@PostMapping
	public ResponseEntity<VisitResponse> createVisit(@Valid @RequestBody VisitRequest request) {
		VisitResponse response = visitService.createVisit(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VisitResponse> getVisitById(@PathVariable Long id) {
		VisitResponse response = visitService.getVisitById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<VisitResponse>> getVisitsByPatient(@PathVariable Long patientId) {
		List<VisitResponse> responses = visitService.getVisitsByPatient(patientId);
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<List<VisitResponse>> getVisitsByDoctor(@PathVariable Long doctorId) {
		List<VisitResponse> responses = visitService.getVisitsByDoctor(doctorId);
		return ResponseEntity.ok(responses);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VisitResponse> updateVisit(@PathVariable Long id, @Valid @RequestBody VisitRequest request) {
		VisitResponse response = visitService.updateVisit(id, request);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
		visitService.deleteVisit(id);
		return ResponseEntity.noContent().build();
	}
}