package org.example.medsys.controller;


import org.example.medsys.dto.AppUserRequest;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.service.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AdminAccessController {
	
	private final AppUserService appUserService;
	
	@Autowired
	public AdminAccessController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}
	
	// General Access Endpoint
	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Home accessed.");
	}
	
	// Admin-Specific Access (Protected by Role)
	@GetMapping("/test")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> adminAccessMethodRequest() {
		return ResponseEntity.ok("Welcome, Admin! You have access to this endpoint.");
	}
	
	// Admin-Specific Endpoint (Protected)
	@GetMapping("/api/admin/home")
	public ResponseEntity<String> adminAccessApi() {
		return ResponseEntity.ok("Welcome, Admin! You have access to this endpoint.");
	}
	
	// Create a User (Admin Only)
	@PostMapping("/api/admin/create-user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AppUser> createUser(@RequestBody AppUserRequest request) {
		AppUser appUser = appUserService.createAppUser(request.getEgn(), request.getPassword(), request.getRoles());
		return ResponseEntity.status(HttpStatus.CREATED).body(appUser);
	}
}
