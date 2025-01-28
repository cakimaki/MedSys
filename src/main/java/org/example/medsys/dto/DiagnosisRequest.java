package org.example.medsys.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequest {
	@NotBlank(message = "Diagnosis name cannot be blank")
	private String name;
}
