package com.gpro.consulting.tiers.logistique.rest.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;


public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        UserValue parsedUser = jwtUtil.parseToken(token);

        if (parsedUser == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }
        //System.out.println("size : " +parsedUser.getRoles().size());
        
        List<GrantedAuthority> authorityList = new ArrayList<>();
        
        authorityList.add(new SimpleGrantedAuthority(parsedUser.getRoleNames()));
        
      /*  Iterator<RoleValue> it = parsedUser.getRoles().iterator();
        while(it.hasNext()){
    
        	String role = it.next().getDesignation();
        	
        	//authorityList.add(new SimpleGrantedAuthority(it.next().getDesignation()));
        	authorityList.add(new SimpleGrantedAuthority(role));
        	
        	  System.out.println(role);
        	
        }*/
        
        ArrayList<RoleValue> roles = new ArrayList<RoleValue>(parsedUser.getRoles());
        for(RoleValue role : roles) {
        
        	authorityList.add(new SimpleGrantedAuthority(role.getDesignation()));
        	
        }
        
        

    // List<GrantedAuthority> authorityLists = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRoleNames().toString());
        
//return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUserName(), token, authorityList,parsedUser.getRoles());
        return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUserName(), token, authorityList,parsedUser.getRoles());
    }
}