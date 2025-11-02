package org.example.medsys.entity.medical;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.medsys.entity.auth.AppUser;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user; //link to AppUser for authentication.
	
	@Column(nullable = false)
	private String name;

    @Column(nullable = false)
    private boolean active = true;

	@ManyToMany
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "specialization_id"))
	private List<Specialization> specializations = new ArrayList<>();
	
	@OneToMany(mappedBy = "gp")
	private List<Patient> patients;
	
	@OneToMany(mappedBy = "doctor")
	private List<Visit> visits;


}
