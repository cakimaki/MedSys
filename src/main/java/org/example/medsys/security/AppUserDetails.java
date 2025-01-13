package org.example.medsys.security;

import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetails implements UserDetailsService {
	
	private final AppUserRepository userRepository;
	
	@Autowired
	public AppUserDetails(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByEgn(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with EGN: " + username));
		
		return User.builder()
				.username(user.getEgn()) // Use EGN as the username
				.password(user.getPassword()) // Encoded password
				.roles(user.getRoleList().stream()
						.map(Role::getName) // Map each Role object to its name
						.toArray(String[]::new)) // Convert to String array
				.build();
	}
}