package org.example.medsys.dto.medical.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {
	private Long id;
	private LocalDateTime dateTime;
	private String doctorName;
	private String diagnosisName;
	private LocalDate sickLeaveStartDate;
	private Integer sickLeaveDurationDays;
}
