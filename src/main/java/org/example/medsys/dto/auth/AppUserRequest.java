package org.example.medsys.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AppUserRequest {
	@NotBlank
	@NotEmpty
	private String egn;
	
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@NotBlank
	@NotEmpty
	private String password;
	
	private List<String> roles;
}