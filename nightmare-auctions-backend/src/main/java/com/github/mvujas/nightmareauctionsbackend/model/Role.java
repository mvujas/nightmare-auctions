package com.github.mvujas.nightmareauctionsbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "role")
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4374301132773236826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public Role(String name) {
		super();
		this.name = name;
	}

	public Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

	// GrantedAuthority required method
	@Override
	public String getAuthority() {
		final String springStandardAuthorityPrefix = "ROLE_";
		return springStandardAuthorityPrefix + name;
	}

}
