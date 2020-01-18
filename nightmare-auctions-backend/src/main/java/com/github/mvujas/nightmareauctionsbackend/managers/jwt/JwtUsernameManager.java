package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUsernameManager {

	@Autowired
	private JwtConverter jwtConverter;
	
	@Autowired
	private JwtPacker jwtPacker;
	
	private final long duration = 5;
	private final TimeUnit durationUnit = TimeUnit.HOURS;
	
	private final String issuer = "Nightmare Auctions";
	
	public String getTokenUsingUsername(String username) {
		return jwtConverter
			.create()
			.subject(username)
			.periodFromNow(duration, durationUnit)
			.issuer(issuer)
			.build();
	}
	
	public void setAuthorizationHeaderUsingUserId(
			HttpServletResponse response, String username) {
		jwtPacker.setAuthorizationHeader(response, getTokenUsingUsername(username));
	}

}
