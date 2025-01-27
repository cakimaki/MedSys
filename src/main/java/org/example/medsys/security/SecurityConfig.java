package org.example.medsys.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final CustomUserDetailsService userDetailsService;
	private final RSAKeyProperties rsaKeyProperties;
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService userDetailsService, RSAKeyProperties rsaKeyProperties) {
		this.userDetailsService = userDetailsService;
		this.rsaKeyProperties = rsaKeyProperties;
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authManager() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(daoProvider);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
		return http
				.cors(cors -> cors.configurationSource(corsConfigurationSource)) // Use the defined CORS configuration
				.csrf(AbstractHttpConfigurer::disable) //stopped csrf protection (postman)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/**", "/home", "/","/test").permitAll()
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/doctor/**").hasAnyRole("DOCTOR","ADMIN")
						.requestMatchers("/api/patient/**").hasAnyRole("ADMIN","DOCTOR","PATIENT")
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults()) // Enable Basic Auth
				
				.oauth2ResourceServer( (oauth2) -> oauth2
						.jwt(jwtConfigurer ->
								jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
				)
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				/*	.formLogin(form -> form
							.permitAll() // Allow form login
							.defaultSuccessUrl("/home", true) // Redirect to home on success
					)*/
				.build();
	}
	@Bean
	public JwtDecoder jwtDecoder(){
		return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.getPublicKey()).build();
	}
	@Bean
	public JwtEncoder jwtEncoder(){
		JWK jwk = new RSAKey.Builder(rsaKeyProperties.getPublicKey()).privateKey(rsaKeyProperties.getPrivateKey()).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter(){
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
		
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtConverter;
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://example.com")); // Add your allowed origins here
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Specify allowed methods
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-CSRF-TOKEN"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L); // How long the response to the pre-flight request can be cached
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
		return source;
	}
	
	/*	@Bean
		public UserDetailsService userDetailsService(){
			UserDetails normalUser = User.builder()
					.username("gc")
					.password("$2a$12$UWkJZvySvaSwyz59EIUxC.u8UGGvMIIWOEUEojGstNfmhuWhHnM0W")
					.roles("USER")
					.build();
			UserDetails adminUser = User.builder()
					.username("admin")
					.password("$2a$12$5PLtUX88US1M/qydLpTL3Oz0bb9sqb5MUvzdR/aZrDxen4IK3Cwd.")
					.roles("USER","ADMIN")
					.build();
			return new InMemoryUserDetailsManager(normalUser,adminUser);
		}*/
	
}
