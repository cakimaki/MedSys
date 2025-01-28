package org.example.medsys.dto.medical;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRequest {
	
	@NotNull(message = "Patient ID cannot be null")
	private Long patientId;
	
	@NotNull(message = "Doctor ID cannot be null")
	private Long doctorId;
	
	private Long diagnosisId;
	
	@NotNull(message = "Date and time cannot be null")
	private LocalDateTime dateTime;
	
	@Size(max = 500, message = "Treatment must not exceed 500 characters")
	private String treatment;
	
	@Size(max = 1000, message = "Notes must not exceed 1000 characters")
	private String notes;
}