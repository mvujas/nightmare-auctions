package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtConstantUserIdentifierTests {

	@Autowired
	JwtConverter jwtConverter;
	
	private String testIssuer = "issuer";
	
	private String createAllInToken() {
		return jwtConverter
				.create()
				.issuer(testIssuer)
				.build();
	}
	
	@Test
	void issuerTest() {
		String token = createAllInToken();
		String issuer = jwtConverter.decode(token).getIssuer();
		assertEquals(testIssuer, issuer);
	}
	

}
