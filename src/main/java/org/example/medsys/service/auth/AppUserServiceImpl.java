package org.example.medsys.service.auth;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.example.medsys.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public AppUser createAppUser(String egn, String password, List<String> roles) {
		// Check if the EGN already exists
		if (appUserRepository.findByEgn(egn).isPresent()) {
			throw new RuntimeException("EGN already exists in the system");
		}
		
		// Fetch and assign roles
		List<Role> roleList = roles.stream()
				.map(roleName -> roleRepository.findByName(roleName)
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
				.toList();
		
		// Create and save AppUser
		AppUser appUser = new AppUser();
		appUser.setEgn(egn);
		appUser.setPassword(passwordEncoder.encode(password)); // Encode the password
		appUser.setRoleList(roleList);
		return appUserRepository.save(appUser);
	}
}