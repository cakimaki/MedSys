package org.example.medsys.service.auth.impl;

import lombok.AllArgsConstructor;
import org.example.medsys.dto.auth.AuthRequest;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.example.medsys.repository.auth.RoleRepository;
import org.example.medsys.security.TokenService;
import org.example.medsys.service.auth.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public String login(String egn, String password) {
		// Authenticate user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(egn, password)
		);
		
		// Fetch user details
		AppUser user = appUserRepository.findByEgn(egn)
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
		
		// Generate and return JWT
		return tokenService.generateJwt(authentication, user);
	}
	
	@Override
	public void register(AuthRequest request) {
		// Check if the user already exists
		if (appUserRepository.findByEgn(request.getEgn()).isPresent()) {
			throw new RuntimeException("A user with this EGN already exists");
		}
		
		// Find or create the default role (PATIENT)
		Role role = roleRepository.findByName("PATIENT")
				.orElseGet(() -> roleRepository.save(new Role(null, "PATIENT")));
		
		// Create and save the new user
		AppUser user = new AppUser();
		user.setEgn(request.getEgn());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoleList(List.of(role));
		
		appUserRepository.save(user);
	}
}


