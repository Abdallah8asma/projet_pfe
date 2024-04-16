package com.gpro.consulting.tiers.logistique.rest.security;
//package com.gpro.consulting.tiers.commun.rest.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//
//public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public JwtAuthenticationFilter() {
//        super("/**");
//    }
//
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        return true;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            throw new JwtTokenMissingException("No JWT token found in request headers");
//        }
//
//        String authToken = header.substring(7);
//
//        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
//
//        return getAuthenticationManager().authenticate(authRequest);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//
//        // As this authentication is in HTTP header, after success we need to continue the request normally
//        // and return the response as if the resource was not secured at all
//        chain.doFilter(request, response);
//    }

//import java.io.IOException; 
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//private AuthenticationManager authenticationManager;
//
//public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//	super();
//	this.authenticationManager = authenticationManager;
//}
//
//@Override
//	public Authentication attemptAuthentication(HttpServletRequest request,
//			HttpServletResponse response) throws AuthenticationException {
//   try{
//	   UserValue user =new ObjectMapper().readValue(request.getInputStream(),UserValue.class);
//	   return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
//	   
//   }
//   catch(IOException e){
//	   e.printStackTrace();
//	   throw new RuntimeException(e);
//   }
//  
//}
//
//@Override
//	protected void successfulAuthentication(HttpServletRequest request,
//			HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//	
//	org.springframework.security.core.userdetails.User user=(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
//	List<String> roles=new ArrayList<String>();
//
//    for(GrantedAuthority a: authResult.getAuthorities())
//   
// 		roles.add(a.getAuthority());
//
//String jwt=JWT.create()
//.withIssuer(request.getRequestURI())
//.withSubject(user.getUsername())
//.withArrayClaim("roles",roles.toArray(new String[roles.size()]))
//.withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
//.sign(Algorithm.HMAC256(SecurityParams.SECRET));
//response.addHeader(SecurityParams.JWT_HEADER_NAME	,jwt);
//
//}
//
//
//}
//
////}
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JWTAuthenticationFilter() {
        super("/**");
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
}
