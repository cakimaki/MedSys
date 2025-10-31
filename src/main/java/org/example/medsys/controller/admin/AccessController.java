package org.example.medsys.controller.admin;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AccessController {

	// General Access Endpoint
	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Home accessed.");
	}
	
	// Doctor Enpoint
	@GetMapping("/api/doctor/home")
	public ResponseEntity<String> doctorAccessApi(){
		return ResponseEntity.ok("Doctor endpoint works.");
	}
	
	// Patient Enpoint
	@GetMapping("/api/patient/home")
	public ResponseEntity<String> patientAccessApi(){
		return ResponseEntity.ok("Patient endpoint works.");
	}
	
	// Admin Endpoint
	@GetMapping("/api/admin/home")
	public ResponseEntity<String> adminAccessApi() {
		return ResponseEntity.ok("Admin endpoint works.");
	}
	


}
