package com.lucasangelo.crudsimples.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.lucasangelo.crudsimples.models.enums.Profile;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String password, Set<Profile> profiles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = profiles.stream().map(profile -> new SimpleGrantedAuthority(profile.getDescription()))
				.collect(Collectors.toList());
	}

	public Integer getId() {
		return this.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
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

	public boolean hasRole(Profile Profile) {
		return getAuthorities().contains(new SimpleGrantedAuthority(Profile.getDescription()));
	}
}