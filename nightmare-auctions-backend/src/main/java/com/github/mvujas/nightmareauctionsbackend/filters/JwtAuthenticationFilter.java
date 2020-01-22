package com.github.mvujas.nightmareauctionsbackend.filters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import com.github.mvujas.nightmareauctionsbackend.model.Role;
import com.github.mvujas.nightmareauctionsbackend.model.User;

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

class LoginResponseBody {
	private String username;
	private List<String> roles;
	
	public LoginResponseBody(User user) {
		if(user == null) {
			throw new NullPointerException(
					"User for response cannot be null");
		}
		this.username = user.getUsername();
		this.roles = user
				.getRoles()
				.stream()
				.map(Role::getName)
				.collect(Collectors.toList());
	}

	public String getUsername() {
		return username;
	}

	public List<String> getRoles() {
		return roles;
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
		
		User principal = (User)authResult.getPrincipal();
		
		jwtManager.setAuthorizationHeaderUsingUserId(
				response, principal.getUsername());
		
		// TODO: Refactor eventually
		String body = new ObjectMapper()
				.writeValueAsString(new LoginResponseBody(principal));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(body).flush();
	}

}
