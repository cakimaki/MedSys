package org.example.medsys.security;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
public class KeyGeneratorUtility {
	
	public static KeyPair generateRsaKey() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			return generator.generateKeyPair();
		} catch (Exception e) {
			throw new IllegalStateException("Failed to generate RSA key pair", e);
		}
	}
}