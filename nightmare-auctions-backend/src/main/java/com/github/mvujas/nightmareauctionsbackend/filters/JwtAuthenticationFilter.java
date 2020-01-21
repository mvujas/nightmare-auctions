package com.github.mvujas.nightmareauctionsbackend.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtUsernameManager;

class LoginData {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	@SuppressWarnings("unused")
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	@SuppressWarnings("unused")
	public void setPassword(String password) {
		this.password = password;
	}
}

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JwtUsernameManager jwtManager;
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUsernameManager jwtManager) {
		super();
		this.jwtManager = jwtManager;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginData params = new ObjectMapper()
					.readValue(request.getInputStream(), LoginData.class);
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(
						params.getUsername(), 
						params.getPassword());
		
			Authentication auth = 
					authenticationManager.authenticate(authenticationToken);			
			
			return auth;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetails principal = (UserDetails)authResult.getPrincipal();
		
		jwtManager.setAuthorizationHeaderUsingUserId(
				response, principal.getUsername());
	}

}
