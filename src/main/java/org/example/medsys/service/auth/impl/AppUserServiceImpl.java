package org.example.medsys.service.auth.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.medsys.dto.AppUserResponse;
import org.example.medsys.entity.auth.AppUser;
import org.example.medsys.entity.auth.Role;
import org.example.medsys.repository.auth.AppUserRepository;
import org.example.medsys.repository.auth.RoleRepository;
import org.example.medsys.service.auth.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public AppUser createAppUser(String egn, String password, List<String> roles) {
		// Check if the EGN already exists
		if (appUserRepository.findByEgn(egn).isPresent()) {
			throw new RuntimeException("EGN already exists in the system");
		}
		
		// Fetch and assign roles
		List<Role> roleList = roles.stream()
				.map(roleName -> roleRepository.findByName(roleName)
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
				.toList();
		
		// Create and save AppUser
		AppUser appUser = new AppUser();
		appUser.setEgn(egn);
		appUser.setPassword(passwordEncoder.encode(password)); // Encode the password
		appUser.setRoleList(roleList);

		return appUserRepository.save(appUser);
	}

    private AppUserResponse toResponse(AppUser appUser){

        return new AppUserResponse(
                appUser.getId(),
                appUser.getEgn(),
                appUser.getRoleList()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
    }

    @Transactional
    @Override
    public AppUserResponse getUserByEgn(String egn){
        AppUser user = appUserRepository.findByEgn(egn)
                .orElseThrow(() -> new NoSuchElementException("No user with such egn found."));

        return toResponse(user);
    }

    @Transactional
    public AppUser updateUser (Long id, String egn, String password, List<String> roles){
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No user found with such id."));

        if(egn != null && !egn.isBlank()){
            appUser.setEgn(egn);
        }

        if(password != null && !password.isBlank()){
            appUser.setPassword(passwordEncoder.encode(password));
        }

        if(roles != null && !roles.isEmpty()){
            List<Role> roleEntities = roleRepository.findByNameIn(roles);

            if(roles.size() != roleEntities.size()){
                throw new IllegalArgumentException("One or more roles are invalid.");
            }
            appUser.setRoleList(roleEntities);
        }

        return appUser;
    }

    @Transactional
    @Override
    public void deleteUserById(Long id){
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("User with such id is not found."));

        appUserRepository.delete(user);
    }

}