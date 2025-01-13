package org.example.medsys.security;

import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final AppUserRepository appUserRepository;
	
	public CustomUserDetailsService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<AppUser> userOptional = appUserRepository.findByEgn(email);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		AppUser user = userOptional.get();
		return User.builder()
				.username(user.getEgn())
				.password(user.getPassword())
				.roles(user.getRoleList().stream()
						.map(Role::getName)
						.toArray(String[]::new))
				.build();
	}
}