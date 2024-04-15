package com.gpro.consulting.tiers.commun.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.persistance.login.IUserPersistance;
import com.gpro.consulting.tiers.commun.service.login.IUserService;


public class JwtDemo {
	@Autowired
	  private IUserPersistance userService;

	private String secret="pass";

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

			UserValue u = new UserValue();
			u.setUserName(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			u.setRoleNames((String) body.get("role"));

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
		claims.put("role", u.getRoleNames());

		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}

	public String generateToken2() {
		UserValue u = userService.findByUsername("h");
		 System.out.println("Roles : "+u.getRoles());
		Claims claims = Jwts.claims().setSubject(u.getUserName());
		claims.put("userId", u.getId() + "");
		claims.put("role", u.getRoles());

		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}
	
	public static void main(String[] args) {

		//secret =pass for generating token in demo. Its the same secret in the secured api (set in applciation.properties) so the CURL works.
		//replace the localhost:8380 according to the server port where the api is deployed
		JwtDemo demo = new JwtDemo();
		UserValue u = new UserValue();
		u.setId(1l);
		u.setRoleNames("role");
		u.setUserName("staalla");
		


		String token = demo.generateToken(u);
		
		System.out.println("Bearer "+ token);

		

		UserValue u2 = new UserValue();
		u.setId(1l);
		u.setRoleNames("resp");
		u.setUserName("hach");
		


		String token2 = demo.generateToken(u2);
		demo.parseToken(token2);
		System.out.println("Bearer "+ token2);





	}
}
