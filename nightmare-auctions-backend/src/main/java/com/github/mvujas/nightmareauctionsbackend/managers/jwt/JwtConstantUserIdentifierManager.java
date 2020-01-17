package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtConstantUserIdentifierManager {

	@Autowired
	private JwtConverter jwtConverter;
	
	@Autowired
	private JwtPacker jwtPacker;

	private final String primaryClaimIdentifier = "userId";
	
	private final long duration = 5;
	private final TimeUnit durationUnit = TimeUnit.HOURS;
	
	private final String issuer = "Nightmare Auctions";
	
	public String getTokenUsingUserId(int userId) {
		return jwtConverter
			.create()
			.claim(primaryClaimIdentifier, userId)
			.periodFromNow(duration, durationUnit)
			.issuer(issuer)
			.build();
	}
	
	public void setAuthorizationHeaderUsingUserId(
			HttpServletResponse response, int userId) {
		jwtPacker.setAuthorizationHeader(response, getTokenUsingUserId(userId));
	}

}
