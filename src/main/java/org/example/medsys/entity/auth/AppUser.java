package org.example.medsys.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.medsys.entity.medical.Doctor;
import org.example.medsys.entity.medical.Patient;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String egn; // EGN as username for authentication
	
	@Column(nullable = false)
	private String password; // Encoded password
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roleList;

    //todo
	// Fields for password reset functionality
	private String resetToken;
	
	private LocalDateTime resetTokenExpiry;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Patient patient;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Doctor doctor;
}