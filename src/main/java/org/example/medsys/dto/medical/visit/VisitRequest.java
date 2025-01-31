package org.example.medsys.dto.medical.visit;

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
	
	private Long doctorId;
	
	private Long diagnosisId;
	
	private LocalDateTime dateTime;
	
	@Size(max = 500, message = "Treatment must not exceed 500 characters")
	private String treatment;
	
	@Size(max = 1000, message = "Notes must not exceed 1000 characters")
	private String notes;
}