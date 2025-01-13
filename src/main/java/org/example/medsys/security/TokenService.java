package org.example.medsys.security;

import org.example.medsys.entity.auth.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TokenService {
	
	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;
	
	public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder = jwtDecoder;
	}
	
	public String generateJwt(Authentication authentication, AppUser user) {
		Instant now = Instant.now();
		
		// Extract roles from the user's role list
		List<String> roles = user.getRoleList().stream()
				.map(role -> "ROLE_" + role.getName()) // Add ROLE_ prefix
				.toList();
		
		// Build JWT claims
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.subject(authentication.getName()) // Authenticated username
				.claim("roles", roles) // Add roles as a claim
				.issuedAt(now) // Issue time
				.expiresAt(now.plus(1, ChronoUnit.HOURS)) // Expiration time
				.build();
		
		// Generate and return the token
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
	public String extractSubject(String token) {
		Jwt jwt = jwtDecoder.decode(token);
		return jwt.getClaim("sub"); // Extract the subject claim (username/EGN)
	}
}

