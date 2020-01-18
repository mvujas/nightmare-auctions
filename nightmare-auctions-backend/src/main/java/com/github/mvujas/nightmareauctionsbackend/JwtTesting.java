package com.github.mvujas.nightmareauctionsbackend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtConstantUserIdentifierManager;
import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtConverter;

@Component
public class JwtTesting {

	@Autowired
	JwtConstantUserIdentifierManager jwtManager;
	
	@Autowired
	JwtConverter jwtConverter;
	
	@PostConstruct
	public void test() {
//		String token = jwtManager.getTokenUsingUserId(15);
//		
//		System.out.println(token);
//		
//		System.out.println(jwtConverter.decode(token).getIssuer());
//		System.out.println(jwtConverter.decode(token).getExpiresAt());
//		System.out.println(jwtConverter.decode(token).getIssuedAt());
//		System.out.println(jwtConverter.decode(token).getClaim("userId").asInt());
	}

}
