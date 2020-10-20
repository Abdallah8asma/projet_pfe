package com.gpro.consulting.tiers.commun.rest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;

public class AuthenticatedUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String job;
    private final String token;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Collection<? extends RoleValue> operations;

/*    public AuthenticatedUser(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token; 
        this.authorities = authorities;
    }*/
    
    public AuthenticatedUser(Long id, String username,String firstName,String lastName,String email,String job, String token, Collection<? extends GrantedAuthority> authorities, Collection<? extends RoleValue> operations) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.job = job;
        this.token = token; 
        this.authorities = authorities;
        this.operations = operations;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    
    @JsonIgnore
    public Collection<? extends RoleValue> getOperations() {
		return operations;
	}

	@Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    
    

    @Override
    public String getPassword() {
        return null;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getJob() {
		return job;
	}

	public String getEmail() {
		return email;
	}

}
