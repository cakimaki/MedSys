package org.example.medsys.dto.medical.doctor;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorResponse {
	
	private Long id;
	
	private String name;
	
	private String egn;
	
	private List<String> specializations;

    private boolean active;
}