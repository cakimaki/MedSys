package org.example.medsys.entity.medical;


import jakarta.persistence.*;
import lombok.*;
import org.example.medsys.entity.auth.AppUser;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user; // Link to AppUser for authentication
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "gp_id", nullable = false)
	private Doctor gp;
	
	@OneToMany(mappedBy = "patient")
	private List<Visit> visits;
	
	@Column(nullable = false)
	private boolean isInsured; // Insurance status
}