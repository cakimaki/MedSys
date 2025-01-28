package org.example.medsys.dto.medical.patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponse {
	private Long id;
	private String name;
	private String egn;
	private String gpName; // Optional field for GP name
	private boolean insured;
}
