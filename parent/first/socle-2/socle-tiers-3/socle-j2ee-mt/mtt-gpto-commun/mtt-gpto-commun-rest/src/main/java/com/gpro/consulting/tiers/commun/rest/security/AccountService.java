package com.gpro.consulting.tiers.commun.rest.security;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;


public interface AccountService {

	
	public String createUserWithCryptPassword(UserValue user);
	public UserValue saveUser(String username,String password,String confirmedPassword,String roleName);

	public String save(RoleValue role);
	public UserValue loadUserByUsername(String username);
	public void addRoleToUser(String username,String roleName);
	public String generateToken(String username) ;
	
	public String updateUserWithCryptPassword(UserValue user);

}

