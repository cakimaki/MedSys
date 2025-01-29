package org.example.medsys.dto.medical;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SickLeaveRequest {
	@NotNull(message = "Visit ID cannot be null")
	private Long visitId;
	
	@NotNull(message = "Start date cannot be null")
	private LocalDate startDate;
	
	@NotNull(message = "Duration in days cannot be null")
	private Integer durationDays;
}