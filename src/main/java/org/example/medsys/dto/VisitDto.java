package org.example.medsys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {
	private Long id;
	private LocalDate date;
	private String doctorName;
	private String diagnosisName;
	private LocalDate sickLeaveStartDate;
	private Integer sickLeaveDurationDays;
}
