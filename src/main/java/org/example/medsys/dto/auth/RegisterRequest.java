package org.example.medsys.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
	private String egn;
	private String password;
}
