package org.example.medsys.dto.medical.specialization;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecializationRequest {
	@NotBlank(message = "Specialization name cannot be blank.")
	private String name;
}
