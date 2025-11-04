package org.example.medsys.dto.medical.visit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class VisitRequest {
	
	@NotNull(message = "Patient ID cannot be null")
	private Long patientId;
	
	private Long doctorId;

    @NotNull(message = "Diagnosis id should not be null")
    @Size(min = 1, message = "At least one diagnosis ID is required")
    private List<Long> diagnosisIds;
	
	private LocalDateTime dateTime;
	
	@Size(max = 500, message = "Treatment must not exceed 500 characters")
	private String treatment;
	
	@Size(max = 1000, message = "Notes must not exceed 1000 characters")
	private String notes;
}