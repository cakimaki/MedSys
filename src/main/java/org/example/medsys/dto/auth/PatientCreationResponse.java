package org.example.medsys.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreationResponse {
	
	private Long id;
	private String egn;
	private String password;
	private Long gpId;
	private String name;
	private boolean isInsured; // Defaults to `false` if not provided
}
