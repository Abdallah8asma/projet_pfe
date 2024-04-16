package com.gpro.consulting.tiers.logistique.rest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;


public class AuthenticatedUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String token;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Collection<? extends RoleValue> operations;

/*    public AuthenticatedUser(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token; 
        this.authorities = authorities;
    }*/
    
    public AuthenticatedUser(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities, Collection<? extends RoleValue> operations) {
        this.id = id;
        this.username = username;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    
    

    @Override
    public String getPassword() {
        return null;
    }

}
