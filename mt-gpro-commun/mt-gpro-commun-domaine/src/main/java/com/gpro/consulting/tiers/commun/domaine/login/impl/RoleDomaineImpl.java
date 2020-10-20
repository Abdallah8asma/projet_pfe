package com.gpro.consulting.tiers.commun.domaine.login.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.domaine.login.IRoleDomaine;
import com.gpro.consulting.tiers.commun.persistance.login.IRolePersistance;
/**
 * implementation of {@link  IRoleDomaine}
 * 
 * @author Nour
 *
 */
@Component
public class RoleDomaineImpl implements IRoleDomaine {
	private static final Logger logger = LoggerFactory.getLogger(RoleDomaineImpl.class);

	@Autowired
	private IRolePersistance rolePersistance ;


	
	@Override
	public String create(RoleValue operationValue){
	    return rolePersistance.create(operationValue);
	}
	@Override
	public String update(RoleValue operationValue){
		return rolePersistance.update(operationValue);
	}
	@Override
	public void delete(Long id){
		rolePersistance.delete(id);
	}
	@Override
	public List<RoleValue> getAll(){
		List<RoleValue> listRoles = rolePersistance.getAll();
		return listRoles ;
	}
	
	public IRolePersistance getRolePersistance() {
		return rolePersistance;
	}
	public void setRolePersistance(IRolePersistance rolePersistance) {
		this.rolePersistance = rolePersistance;
	}
	@Override
	public RoleValue getById(Long id) {
		// TODO Auto-generated method stub
		return rolePersistance.getById(id);
	}
	@Override
	public List<RoleValue> rechercherMultiCritere(RoleValue role) {
		// TODO Auto-generated method stub
		return rolePersistance.rechercherMultiCritere(role);
	}
	@Override
	public RoleValue getByCode(String code) {
		// TODO Auto-generated method stub
		return rolePersistance.getByCode(code);
	}
}
