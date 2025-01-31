package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InsurancePayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="patient_id",nullable = false)
	private Patient patient;
	
	@Column(nullable = false)
	private LocalDate month;
	
	@Column(nullable = false)
	private boolean isPaid;
}
