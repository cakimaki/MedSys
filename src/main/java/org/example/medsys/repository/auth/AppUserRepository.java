package org.example.medsys.repository.auth;

import org.example.medsys.entity.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByEgn(String egn);
	
	Optional<AppUser> findByResetToken(String resetToken);
}