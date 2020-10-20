package com.gpro.consulting.tiers.commun.domaine.login.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.domaine.login.IUserDomaine;
import com.gpro.consulting.tiers.commun.persistance.login.IRolePersistance;
import com.gpro.consulting.tiers.commun.persistance.login.IUserPersistance;


/**
 * implementation of {@link IUserDomaine}
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */

@Component
public class UserDomaineImpl implements IUserDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDomaineImpl.class);
	
	@Autowired
	private IUserPersistance userPersistance;

	@Autowired
	private IRolePersistance rolePersistance;
	
	public IRolePersistance getRolePersistance() {
		return rolePersistance;
	}

	public void setRolePersistance(IRolePersistance rolePersistance) {
		this.rolePersistance = rolePersistance;
	}

	@Override
	public String create(UserValue userValue) {
	
	/*	Set <String> listRoles= userValue.getListRoles();
		String resultRoles = null;
		String roles = "" ;
		for(String role : listRoles)
		{
			roles +=  role+"-";

			
			if((roles!=null)&&(roles.length()>0)) {
				resultRoles = roles.substring(0, roles.length() - 1);
		}
			userValue.setRoleNames(resultRoles);}
			
		*/	
		
		return userPersistance.create(userValue);
		
	}

	@Override
	public UserValue getById(Long id) {
		
		UserValue User = userPersistance.getById(id);
		
		return User;
	}

	@Override
	public String update(UserValue userValue) {
	/*	Set <String> listRoles= userValue.getListRoles();
		String resultRoles = null;
		String roles = "" ;
		for(String role : listRoles)
		{
			roles +=  role+"-";

			
			if((roles!=null)&&(roles.length()>0)) {
				resultRoles = roles.substring(0, roles.length() - 1);
		}
			userValue.setRoleNames(resultRoles);}*/
		
		return userPersistance.update(userValue);
	}

	@Override
	public void delete(Long id) {
		
		userPersistance.delete(id);
	}

	@Override
	public List<UserValue> getAll() {
		
		List<UserValue> listUser = userPersistance.getAll();
		
		return listUser;
	}

	@Override
	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request) {
		
		ResultatRechecheUserValue result = userPersistance.rechercherMultiCritere(request);
		
		return result;
	}
	
@Override
	public UserValue login(UserValue request) {
		
		UserValue user = userPersistance.login(request);
		
	//	if (user != null)user.setVersion(user.getRoleNames());
			
		
			

		

	
		return user;
	}

	


	public IUserPersistance getUserPersistance() {
		return userPersistance;
	}

	public void setUserPersistance(IUserPersistance userPersistance) {
		this.userPersistance = userPersistance;
	}

	@Override
	public UserValue findByUsername(String username) {
		// TODO Auto-generated method stub
		return userPersistance.findByUsername(username);
	}
	
	
	
	
}
