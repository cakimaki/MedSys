package org.example.medsys.dto.medical.diagnosis;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequest {
	@NotBlank(message = "Diagnosis name could not be blank.")
	private String name;
}
