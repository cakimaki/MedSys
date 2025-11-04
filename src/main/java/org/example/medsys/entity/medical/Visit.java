package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@ManyToMany
    @JoinTable(
            name = "visit_diagnosis",
            joinColumns = @JoinColumn(name = "visit_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id")
    )
	private List<Diagnosis> diagnoses;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@OneToOne(mappedBy = "visit", cascade = CascadeType.ALL)
	private SickLeave sickLeave;
	
	@Column(length = 500)
	private String treatment;
	
	@Column(length = 1000)
	private String notes;
}