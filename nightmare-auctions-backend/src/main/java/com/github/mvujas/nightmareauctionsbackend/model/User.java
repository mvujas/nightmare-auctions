package com.github.mvujas.nightmareauctionsbackend.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.ItemPresentationView;
import com.github.mvujas.nightmareauctionsbackend.util.TimeUtils;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {
	"password"
})
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3912356527437407900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ItemPresentationView.SummaryView.class)
	private int id;
	
	@Column(unique = true, nullable = false)
	@JsonView(ItemPresentationView.SummaryView.class)
	private String username;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(updatable = false, nullable = false)
	private Timestamp registrationTime;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role")
	private List<Role> roles;
	
	@OneToMany(mappedBy = "author")
	private List<Item> items;

	
	@PrePersist
	protected void onCreate() {
		registrationTime = TimeUtils.getCurrentTimestamp();
	}
	
	
	public User() {
		super();
	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles="
				+ roles + "]";
	}

	
	// UserDetails methods beginning
	
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return roles;
		}
	
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
	
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
	
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
	
		@Override
		public boolean isEnabled() {
			return true;
		}
	
	// UserDetails methods end

}
