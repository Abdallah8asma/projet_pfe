package com.gpro.consulting.tiers.commun.rest.security;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.persistance.login.IRolePersistance;
import com.gpro.consulting.tiers.commun.service.login.IUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private IUserService userRepository;
	@Autowired
	private IRolePersistance appRoleRepository;
	@Autowired
	private IUserService userService;
	
	@Bean
	static BCryptPasswordEncoder getBCPE(){
		
		return new BCryptPasswordEncoder();
	}	

	public UserValue saveUser(String username, String password,String confirmedPassword, String roleName) {
	
		UserValue user=userRepository.findByUsername(username);
		if(user.getUserName()!=null) throw new RuntimeException("Utilsateur existe déja");
		if(!password.equals(confirmedPassword))throw new RuntimeException("Password non confirmé");
		UserValue userr=new UserValue();
			userr.setUserName(username);	
			userr.setPassword(getBCPE().encode(password) );
			userr.setRoleNames(roleName);
			//System.out.print("PASS="+userr.getPassword());
			
			userService.create(userr);
		return userr;
	}

	public String save(RoleValue role) {
		// TODO Auto-generated method stub
		 appRoleRepository.create(role);
		 return "ok";
	}

	public UserValue loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	public void addRoleToUser(String username, String roleName) {
 
		UserValue user=userRepository.findByUsername(username);
		//RoleValue role=appRoleRepository.findByRoleName(roleName);
		
		//user.getRoles().add(role);
	}
	private String secret="pass";

	public String generateToken(String username) {
		UserValue u = userService.findByUsername(username);
		 //System.out.println("Roles : "+u.getRoleNames());
		Claims claims = Jwts.claims().setSubject(u.getUserName());
		claims.put("userId", u.getId() + "");
		claims.put("boutiqueId", u.getBoutiqueId() + "");
		claims.put("role", u.getRoleNames());
		//claims.put("operations", u.getRoles());
		
		List<String> codesOperations = new ArrayList<>();
		for(RoleValue role : u.getRoles()) 
			codesOperations.add(role.getCode());
			
		claims.put("operations", codesOperations);
	    //claims.put("operations", Arrays.asList(codesOperations));
		
		 
		
//System.out.println(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,secret).compact());
		
this.parseToken(Jwts.builder().setClaims(claims)
		.signWith(SignatureAlgorithm.HS512,secret).compact());
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}
	public UserValue parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret)
					.parseClaimsJws(token).getBody();

			UserValue u = new UserValue();
			u.setUserName(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			
			u.setBoutiqueId(Long.parseLong((String) body.get("boutiqueId")));
			
			u.setRoleNames(body.get("role").toString());
			
			//u.setRoles((Collection<RoleValue>) body.get("operations"));

			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	@Override
	public String createUserWithCryptPassword(UserValue vUser) {
		UserValue user=userRepository.findByUsername(vUser.getUserName());
		if(user.getUserName()!=null) throw new RuntimeException("Utilsateur existe déja");
	
		vUser.setPassword(getBCPE().encode(vUser.getPassword()) );
		
		return	userService.create(vUser);
		
	}

	@Override
	public String updateUserWithCryptPassword(UserValue user) {
		// TODO Auto-generated method stub
		user.setPassword(getBCPE().encode(user.getPassword()) );
		
		return	userService.update(user);
	}


}


