package org.example.medsys.dto.medical.sickleave;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SickLeaveResponse {
	private Long id;
	private Long visitId;
	private LocalDate startDate;
	private Integer durationDays;
}
