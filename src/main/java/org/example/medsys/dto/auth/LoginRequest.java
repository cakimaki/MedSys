package org.example.medsys.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
	private String egn;
	private String password;
}
