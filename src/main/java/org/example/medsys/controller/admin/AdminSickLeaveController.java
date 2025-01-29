package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.SickLeaveRequest;
import org.example.medsys.dto.medical.SickLeaveResponse;
import org.example.medsys.dto.medical.sickleave.SickLeaveUpdateRequest;
import org.example.medsys.service.medical.SickLeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sickleave")
public class AdminSickLeaveController {
	
	private final SickLeaveService sickLeaveService;
	
	public AdminSickLeaveController(SickLeaveService sickLeaveService) {
		this.sickLeaveService = sickLeaveService;
	}
	
	@PostMapping
	public ResponseEntity<SickLeaveResponse> createSickLeave(@Valid @RequestBody SickLeaveRequest request) {
		SickLeaveResponse response = sickLeaveService.createSickLeave(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/visit/{visitId}")
	public ResponseEntity<SickLeaveResponse> getSickLeaveByVisitId(@PathVariable Long visitId) {
		SickLeaveResponse response = sickLeaveService.getSickLeaveByVisitId(visitId);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SickLeaveResponse> updateSickLeave(
			@PathVariable Long id, @Valid @RequestBody SickLeaveUpdateRequest request) {
		SickLeaveResponse response = sickLeaveService.updateSickLeave(id, request);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSickLeave(@PathVariable Long id) {
		sickLeaveService.deleteSickLeave(id);
		return ResponseEntity.noContent().build();
	}
}