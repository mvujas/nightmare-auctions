package com.github.mvujas.nightmareauctionsbackend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtConverter;
import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtUsernameManager;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.repositories.UserRepository;

@Component
public class JwtTesting {

	@Autowired
	JwtUsernameManager jwtManager;
	
	@Autowired
	JwtConverter jwtConverter;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo;
	
	//@PostConstruct
	public void test() {
		User user = userRepo.findByUsername("mvujas");
		System.out.println("User: " + user);
		System.out.println("Does password match(T): " + passwordEncoder.matches("maliPerica", user.getPassword()));
		System.out.println("Does password match(F): " + passwordEncoder.matches("sifra123", user.getPassword()));
		
		User admin = userRepo.findByUsername("admin");
		System.out.println("Admin: " + admin);
		System.out.println("Does password match(T): " + passwordEncoder.matches("admin123", admin.getPassword()));
		System.out.println("Does password match(F): " + passwordEncoder.matches("nekaSifra", admin.getPassword()));
		
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
