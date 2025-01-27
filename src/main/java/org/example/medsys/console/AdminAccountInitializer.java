package org.example.medsys.console;

import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.entity.medical.Specialization;
import org.example.medsys.repository.auth.AppUserRepository;
import org.example.medsys.repository.auth.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AdminAccountInitializer {
	
	@Bean
	public CommandLineRunner createAdminUser(
			AppUserRepository appUserRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			
			// Check if an admin account already exists
			if (appUserRepository.findByEgn("admin").isEmpty()) {
				// Create ADMIN role if it doesn't exist
				Role adminRole = roleRepository.findByName("ADMIN")
						.orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));
				
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
