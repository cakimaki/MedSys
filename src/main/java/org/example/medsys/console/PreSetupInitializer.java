package org.example.medsys.console;

import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.example.medsys.repository.auth.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PreSetupInitializer {
	
	//
	@Bean
	public CommandLineRunner createAdminUser(
			AppUserRepository appUserRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			
			// Create roles if they don't exist
			List<String> roleNames = Arrays.asList("ADMIN", "DOCTOR", "PATIENT");
			List<Role> roles = new ArrayList<>();
			
			for (String roleName : roleNames) {
				Role role = roleRepository.findByName(roleName)
						.orElseGet(() -> {
							Role newRole = new Role(null, roleName);
							return roleRepository.save(newRole);
						});
				roles.add(role);
			}
			System.out.println("Roles ensured in the database: " + roleNames);
			
			// Check if an admin account already exists
			if (appUserRepository.findByEgn("admin").isEmpty()) {
				Role adminRole = roles.stream()
						.filter(role -> role.getName().equals("ADMIN"))
						.findFirst()
						.orElseThrow(() -> new RuntimeException("ADMIN role not found"));
				
				// Create the admin user
				AppUser adminUser = new AppUser();
				adminUser.setEgn("admin");
				adminUser.setPassword(passwordEncoder.encode("admin123")); // Default admin password
				adminUser.setRoleList(Collections.singletonList(adminRole));
				
				appUserRepository.save(adminUser);
				System.out.println("Admin account created with username 'admin' and password 'admin123'");
			} else {
				System.out.println("Admin account already exists");
			}
		};
	}
}
