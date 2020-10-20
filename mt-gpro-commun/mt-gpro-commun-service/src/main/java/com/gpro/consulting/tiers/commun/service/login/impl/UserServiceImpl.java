package com.gpro.consulting.tiers.commun.service.login.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IBoutiqueDomaine;
import com.gpro.consulting.tiers.commun.domaine.login.IUserDomaine;
import com.gpro.consulting.tiers.commun.service.login.IUserService;

/**
 * implementation of {@link IUserService}
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	IUserDomaine userDomaine;
	
	@Autowired
	IBoutiqueDomaine boutiqueDomaine;
	
	@Override
	public String create(UserValue controleValue) {
		
		//logger.info("createUser: Delegating request to Domaine layer.");
		
		return userDomaine.create(controleValue);
	}

	@Override
	public UserValue getById(Long id) {
		
		//logger.info("getUserById: Delegating request {} to Domaine layer."+id);
		
		return userDomaine.getById(id);
	}

	@Override
	public String update(UserValue controleValue) {
		
		//logger.info("updateUser: Delegating request to Domaine layer.");
		
		return userDomaine.update(controleValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("deleteUser: Delegating request {} to Domaine layer."+id);
		
		userDomaine.delete(id);
	}

	@Override
	public List<UserValue> getAll(){
		
		//logger.info("getAll: Delegating request to Domaine layer.");
		
		return userDomaine.getAll();
	}

	@Override
	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request) {
		
		
		
		
		ResultatRechecheUserValue res =  userDomaine.rechercherMultiCritere(request);
		
		for(UserValue user : res.getList()) {
			
			if(user.getBoutiqueId() != null) {
				
				user.setBoutiqueAbreviation(boutiqueDomaine.rechercheBoutiqueParId(new BoutiqueValue(user.getBoutiqueId())).getAbreviation());
				
			}
		
			
		}
		
		
		return res;
	}
	
	@Override
	public UserValue login(UserValue request) {
		
		return userDomaine.login(request);
	}

	public IUserDomaine getUserDomaine() {
		return userDomaine;
	}

	public void setUserDomaine(IUserDomaine userDomaine) {
		this.userDomaine = userDomaine;
	}

	@Override
	public UserValue findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDomaine.findByUsername(username);
	}
	
}
