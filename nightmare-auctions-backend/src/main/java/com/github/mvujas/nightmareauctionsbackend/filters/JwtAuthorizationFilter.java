package com.github.mvujas.nightmareauctionsbackend.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtUsernameManager;
import com.github.mvujas.nightmareauctionsbackend.services.UserService;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JwtUsernameManager jwtManager;
	private UserService userService;
	
	public JwtAuthorizationFilter(
			AuthenticationManager authenticationManager, 
			JwtUsernameManager jwtManager, 
			UserService userService) {
		super(authenticationManager);
		this.jwtManager = jwtManager;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Authentication authentication = getAuthenticationObject(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);
	}
	
	public Authentication getAuthenticationObject(HttpServletRequest request) {
		String username = jwtManager.getUsernameFromRequestHeader(request);
		
		UserDetails user = userService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(
						user.getUsername(), null, user.getAuthorities());
		
		return authenticationToken;
	}

}
