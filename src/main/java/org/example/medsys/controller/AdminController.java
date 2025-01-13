package org.example.medsys.controller;

import org.example.medsys.dto.AppUserRequest;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.service.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/admin")
////@PreAuthorize("hasRole('ADMIN')")
//public class AdminController {
//
//	private final AppUserService appUserService;
//
//	@Autowired
//	public AdminController(AppUserService appUserService) {
//		this.appUserService = appUserService;
//	}
//
//	@PostMapping("/create-user")
//	public ResponseEntity<AppUser> createUser(@RequestBody AppUserRequest request) {
//		AppUser appUser = appUserService.createAppUser(request.getEgn(), request.getPassword(), request.getRoles());
//		return ResponseEntity.status(HttpStatus.CREATED).body(appUser);
//	}
//}