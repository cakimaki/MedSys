package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;
import org.example.medsys.service.medical.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/doctor")
public class AdminDoctorController {
	private final DoctorService doctorService;
	
	public AdminDoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@PostMapping
	public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest request) {
		DoctorResponse response = doctorService.createDoctor(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable long id) {
		doctorService.deleteDoctor(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DoctorResponse> fetchDoctorById(@PathVariable long id) {
		DoctorResponse response = doctorService.fetchDoctorById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<DoctorResponse>> fetchAllDoctors() {
		List<DoctorResponse> responses = doctorService.fetchAllDoctors();
		return ResponseEntity.ok(responses);
	}
}
