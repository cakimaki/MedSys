package org.example.medsys.service.auth;

import org.example.medsys.dto.auth.AuthRequest;

public interface AuthService {
	String login(String egn, String password);
	
	void register(AuthRequest request);
}
