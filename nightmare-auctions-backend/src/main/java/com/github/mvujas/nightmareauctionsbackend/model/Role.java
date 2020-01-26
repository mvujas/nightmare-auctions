package com.github.mvujas.nightmareauctionsbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.RolePresentationView;

@Entity
@Table(name = "role")
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

	@JsonView(RolePresentationView.Identifier.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(RolePresentationView.Name.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(RolePresentationView.Users.class)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

	// GrantedAuthority required method
	@Override
	@JsonView(RolePresentationView.Authority.class)
	public String getAuthority() {
		final String springStandardAuthorityPrefix = "ROLE_";
		return springStandardAuthorityPrefix + name;
	}

}
