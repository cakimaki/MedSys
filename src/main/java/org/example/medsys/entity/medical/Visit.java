package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "diagnosis_id")
	private Diagnosis diagnosis;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@OneToOne(mappedBy = "visit")
	private SickLeave sickLeave;
	
	
}
