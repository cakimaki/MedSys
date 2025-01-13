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
public class SickLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "visit_id", nullable = false)
	private Visit visit;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private Integer durationDays;
}