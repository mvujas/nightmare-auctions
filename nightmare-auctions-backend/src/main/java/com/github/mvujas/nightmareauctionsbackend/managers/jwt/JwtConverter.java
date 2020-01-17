package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtConverter {

	@Value("${jwt.secret}")
	private String secret;

	private Algorithm algorithm;
	private JWTVerifier verifier;

	@PostConstruct
	public void initialize() {
		algorithm = Algorithm.HMAC256(secret);
		verifier = JWT
				.require(algorithm)
				.build();
	}
	
	
	public JwtTokenBuilder create() {
		return new JwtTokenBuilder(algorithm);
	}
	
	public DecodedJWT decode(String token) {
		return verifier.verify(token);
	}
}
