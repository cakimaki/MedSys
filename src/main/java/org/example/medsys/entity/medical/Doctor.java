package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "specialization_id")
	)
	private List<Specialization> specializations;
	
	@OneToMany(mappedBy = "gp")
	private List<Patient> patients;
	
	@OneToMany(mappedBy = "doctor")
	private List<Visit> visits;
	
}
