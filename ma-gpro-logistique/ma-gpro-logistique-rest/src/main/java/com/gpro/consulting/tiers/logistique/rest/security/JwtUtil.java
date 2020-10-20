package com.gpro.consulting.tiers.logistique.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;

import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Tries to parse specified String as a JWT token. If successful, returns
	 * User object with username, id and role prefilled (extracted from token).
	 * If unsuccessful (token is invalid or not containing all required user
	 * properties), simply returns null.
	 *
	 * @param token
	 *            the JWT token to parse
	 * @return the User object extracted from specified token or null if a token
	 *         is invalid.
	 */
	public UserValue parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret)
					.parseClaimsJws(token).getBody();
			//System.out.println("User creation ...");

			UserValue u = new UserValue();
			//System.out.println("User created ...");

			u.setUserName(body.getSubject());
			//System.out.println("Username persisted ..."+u.getUserName());

			u.setId(Long.parseLong((String) body.get("userId")));
			
			u.setBoutiqueId(Long.parseLong((String) body.get("boutiqueId")));
			
			//System.out.println("ID persisted ..."+u.getId());
			//System.out.println("Roles body ..."+ body.get("role"));

             u.setRoleNames(  body.get("role").toString());
             
     		//System.out.println("Roles body ..."+ body.get("operations"));
			   
     		//Collection<RoleValue> operations = new ArrayList<RoleValue>();
     		
           //  List<RoleValue> operations = (ArrayList<RoleValue>) body.get("operations",RoleValue[].class);
     		//System.out.println("Before CAST : ");
     		//String[] listOperations = body.get("operations",String[].class);
     		//System.out.println("After CAST : ");
     			
     		/*	for(String element : listOperations) {
     				//operations.add(r);
     				
     		    	System.out.println("r : "+element);
     			}*/
             
            // System.out.println("operations.size : "+operations.size());
             
           //  System.out.println("Roles1 : "+operations.get(0).getDesignation());
           //  System.out.println("Roles2 : "+operations.get(1).getDesignation());
             
           /*   for(RoleValue role : operations) {
                 
             	System.out.println("Roles : "+role.getDesignation());
              	
              }*/
              
             
             
             
		//	Collection<RoleValue> operations = new ArrayList<RoleValue> (c);
             
          /*	System.out.println("operations.size : "+operations.size());
          	
          	
             for(RoleValue role : operations) {
                 
            	System.out.println("Roles : "+role.getDesignation());
             	
             }
             */
			// u.setRoles(operations);
			
		//	System.out.println("Roles persisted ..."+u.getRoleNames());

			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	/**
	 * Generates a JWT token containing username as subject, and userId and role
	 * as additional claims. These properties are taken from the specified User
	 * object. Tokens validity is infinite.
	 *
	 * @param u
	 *            the user for which the token will be generated
	 * @return the JWT token
	 */
	public String generateToken(UserValue u) {
		Claims claims = Jwts.claims().setSubject(u.getUserName());
		claims.put("userId", u.getId() + "");
		claims.put("boutiqueId", u.getBoutiqueId() + "");
		claims.put("role", u.getRoleNames());
		//System.out.println("Roles in token ..."+u.getRoleNames());
		
		//claims.put("operations", u.getRoles());
		for(RoleValue role : u.getRoles())
			claims.put("operations", role.getCode());

		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
