package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.specialization.SpecializationRequest;
import org.example.medsys.dto.medical.specialization.SpecializationResponse;
import org.example.medsys.service.medical.SpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class AdminSpecializationController {
	
	private final SpecializationService specializationService;
	
	public AdminSpecializationController(SpecializationService specializationService) {
		this.specializationService = specializationService;
	}
	
	@PostMapping
	public ResponseEntity<SpecializationResponse> createSpecialization(
			@Valid @RequestBody SpecializationRequest request) {
		SpecializationResponse response = specializationService.createSpecialization(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SpecializationResponse> fetchSpecializationById(@PathVariable long id) {
		SpecializationResponse response = specializationService.fetchSpecializationById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<SpecializationResponse>> fetchAllSpecializations() {
		List<SpecializationResponse> responses = specializationService.fetchAllSpecializations();
		return ResponseEntity.ok(responses);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSpecialization(@PathVariable long id) {
		specializationService.deleteSpecialization(id);
		return ResponseEntity.noContent().build();
	}
}

