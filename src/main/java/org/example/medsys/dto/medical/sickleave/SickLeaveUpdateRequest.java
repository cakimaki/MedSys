package org.example.medsys.dto.medical.sickleave;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SickLeaveUpdateRequest {
	@NotNull(message = "Start date cannot be null")
	private LocalDate startDate;
	
	@NotNull(message = "Duration in days cannot be null")
	private Integer durationDays;
}