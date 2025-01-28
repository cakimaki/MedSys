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
	
	@GetMapping
	public ResponseEntity<List<VisitResponse>> getAllVisits() {
		List<VisitResponse> responses = visitService.getAllVisits();
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VisitResponse> getVisitById(@PathVariable Long id) {
		VisitResponse response = visitService.getVisitById(id);
		return ResponseEntity.ok(response);
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