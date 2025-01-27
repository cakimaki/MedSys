package org.example.medsys.dto.medical.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorRequest {
	
	@NotBlank(message = "Name cannot be blank")
	private String name; // Doctor's name
	
	@NotNull(message = "Specialization IDs cannot be null")
	private List<Long> specializationIds; // IDs of the doctor's specializations
	
	@NotBlank(message = "EGN cannot be blank")
	private String egn; // EGN for the AppUser creation
	
	@NotBlank(message = "Password cannot be blank")
	private String password; // Password for the AppUser creation
}
