package org.example.medsys.dto.medical.doctor;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorResponse {
	
	private Long id; // Doctor's ID
	
	private String name; // Doctor's name
	
	private String egn; // EGN for the linked AppUser
	
	private List<String> specializations; // Names of the doctor's specializations
}