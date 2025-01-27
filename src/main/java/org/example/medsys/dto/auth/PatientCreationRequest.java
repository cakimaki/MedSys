package org.example.medsys.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreationRequest {
	@NotBlank(message = "EGN cannot be empty or blank")
	@Size(min = 10, max = 10, message = "EGN must be exactly 10 characters")
	@Pattern(regexp = "\\d{10}", message = "EGN must be a 10-digit number")
	private String egn;
	
	@NotBlank(message = "Password cannot be empty or blank")
	private String password;
	
	@NotNull(message = "GP ID is required")
	private Long gpId;
	
	@NotBlank(message = "Name cannot be empty or blank")
	private String name;
	
	private boolean isInsured; // Defaults to `false` if not provided
}
