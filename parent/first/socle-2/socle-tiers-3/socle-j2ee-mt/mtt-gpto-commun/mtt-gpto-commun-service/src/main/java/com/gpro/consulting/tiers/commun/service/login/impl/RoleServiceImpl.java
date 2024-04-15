package com.gpro.consulting.tiers.commun.service.login.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.domaine.login.IRoleDomaine;
import com.gpro.consulting.tiers.commun.service.login.IRoleService;
/**
 * implementation of {@link IRoleService}
 * 
 * @author Nour
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private IRoleDomaine roleDomaine ;

	
	@Override
	public String create(RoleValue operationValue){
	//	System.out.println("#######################SERVICE############"+operationValue.getDesignation());
		return roleDomaine.create(operationValue);
	}
	
	@Override
	public String update(RoleValue operationValue){
		return roleDomaine.update(operationValue);
	}
	
	@Override
	public void delete(Long id){
		roleDomaine.delete(id);
	}
	
	@Override
	public List<RoleValue> getAll(){
		List<RoleValue> listRoles = roleDomaine.getAll();
		return listRoles;
	}
	
	
	public IRoleDomaine getRoleDomaine() {
		return roleDomaine;
	}

	public void setRoleDomaine(IRoleDomaine roleDomaine) {
		this.roleDomaine = roleDomaine;
	}

	@Override
	public List<RoleValue> rechercherMultiCritere(RoleValue request) {
		// TODO Auto-generated method stub
		return roleDomaine.rechercherMultiCritere(request);
	}

	@Override
	public RoleValue getById(Long id) {
		// TODO Auto-generated method stub
		return roleDomaine.getById(id);
	}

	@Override
	public RoleValue getByCode(String code) {
		// TODO Auto-generated method stub
		return roleDomaine.getByCode(code);
	}
}
