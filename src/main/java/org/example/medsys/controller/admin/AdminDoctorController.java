package org.example.medsys.controller.admin;

import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;
import org.example.medsys.service.medical.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminDoctorController {
	private final DoctorService doctorService;
	
	public AdminDoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@PostMapping("/doctor")
	public ResponseEntity<DoctorResponse> createDoctor(DoctorRequest request){
		try{
			DoctorResponse response = doctorService.createDoctor(request);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
