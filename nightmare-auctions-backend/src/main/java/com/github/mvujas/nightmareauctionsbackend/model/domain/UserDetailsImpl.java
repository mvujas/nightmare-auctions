package com.github.mvujas.nightmareauctionsbackend.model.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.mvujas.nightmareauctionsbackend.model.Role;
import com.github.mvujas.nightmareauctionsbackend.model.User;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8795685672966111480L;
	
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user) {
		super();
		if(user == null) {
			throw new NullPointerException("User cannot be null");
		}
		this.user = user;
		this.authorities = userToAuthorities(user);
	}
	
	public User getUser() {
		return user;
	}

	private GrantedAuthority customRoleToGrantedAuthority(Role role) {
		String springStandardRoleName = "ROLE_" + role.getName();
		return new SimpleGrantedAuthority(springStandardRoleName);
	}
	
	private Collection<? extends GrantedAuthority> userToAuthorities(User user) {
		return user
				.getRoles()
				.stream()
				.map(this::customRoleToGrantedAuthority)
				.collect(Collectors.toSet());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
