package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class JwtPacker {
	private final String authorizationHeaderName = "Authorization";
	private final String authorizationPrefix = "Bearer";
	
	private String createAuthorizationHeader(String token) {
		return authorizationPrefix + " " + token;
	}
	
	public void setAuthorizationHeader(HttpServletResponse response, String token) {
		response.setHeader(authorizationHeaderName, createAuthorizationHeader(token));
	}
	
	public String getTokenFromHeaderValue(String headerValue)
			throws IllegalArgumentException {
		String[] headerParts = headerValue.trim().split(" ");
	
		if(headerParts.length != 2 && headerParts[0].equalsIgnoreCase(authorizationPrefix)) {
			throw new IllegalArgumentException("Given header format is not supported");
		}
		
		return headerParts[1];
	}
	
	public String getToken(HttpServletRequest request)
			throws IllegalArgumentException {
		String headerValue = request.getHeader(authorizationHeaderName);
		
		if(headerValue == null) {
			throw new IllegalArgumentException("Authorization header is not present");
		}
		
		return getTokenFromHeaderValue(headerValue);
	}

}
