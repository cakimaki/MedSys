package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "insurance_payment",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id","month", "year"})
        }
)
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

    // Always stored YYYY-MM-01
	@Column(nullable = false)
	private Integer month;

    @Column(nullable = false)
    private Integer year;
	
	@Column(nullable = false)
	private boolean isPaid;

    @Column(nullable = true)
    private LocalDate paidOn;
}
