package org.example.medsys.dto.medical.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "EGN cannot be empty or blank")
	@Size(min = 10, max = 10, message = "EGN must be exactly 10 characters")
	@Pattern(regexp = "\\d{10}", message = "EGN must be a 10-digit number")
	private String egn;
	
	@NotBlank(message = "Password cannot be blank")
	private String password; // Password for the AppUser creation
}
