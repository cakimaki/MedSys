package org.example.medsys.service.auth;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.AppUserResponse;
import org.example.medsys.entity.auth.AppUser;

import java.util.List;

public interface AppUserService {
	@Transactional
    AppUser createAppUser(String egn, String password, List<String> roles);

    @Transactional
    AppUserResponse getUserByEgn(String egn);

    void deleteUserById(Long id);

    AppUser updateUser (Long id, String egn, String password, List<String> roles);
}
