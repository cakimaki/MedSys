package org.example.medsys.service.auth;

import org.example.medsys.dto.auth.RegisterRequest;

public interface AuthService {
	String login(String egn, String password);
	
	void register(RegisterRequest request);
}
