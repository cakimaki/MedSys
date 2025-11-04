package org.example.medsys.dto.medical.visit;

import lombok.Getter;
import lombok.Setter;
import org.example.medsys.entity.medical.Diagnosis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VisitResponse {
	private Long id;
	private String patientName;
	private String doctorName;
	private List<String> diagnosesNames;
	private LocalDateTime dateTime;
	private String treatment;
	private String notes;
	
	// SickLeave fields
    private Long sickLeaveId;
	private LocalDate sickLeaveStartDate;
	private Integer sickLeaveDurationDays;

}