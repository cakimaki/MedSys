package org.example.medsys.dto.medical;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class VisitResponse {
	private Long id;
	private String patientName;
	private String doctorName;
	private String diagnosisName;
	private LocalDateTime dateTime;
	private String treatment;
	private String notes;
	
	// SickLeave fields
	private LocalDate sickLeaveStartDate;
	private Integer sickLeaveDurationDays;
}