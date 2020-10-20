package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.service.login.IRoleService;
/**
 * 
 * @author Nour
 *
 */

@RestController
@RequestMapping("/role")
public class RoleRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(RoleRestImpl.class);
	
	@Autowired
	private IRoleService roleService ;
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public List<RoleValue> rechercherMultiCritere(@RequestBody RoleValue request) {
		 
		//logger.info("rechercheMulticritere: Delegating request to service layer.");

		return roleService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public RoleValue getById(@PathVariable Long id) {
		  
		//logger.info("getUserById: Delegating request id {} to service layer.", id);
		
		return roleService.getById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody RoleValue operationValue){
		
		
		return roleService.create(operationValue);
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody RoleValue operationValue){
		return roleService.update(operationValue);
	}
	@RequestMapping (value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		roleService.delete(id);
	}
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<RoleValue> getAll(){
		List<RoleValue> listRoles = roleService.getAll();
		return listRoles ;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping(value = "/getAllCodeOperation", method = RequestMethod.GET, produces = "application/json")
	public List<String> getAllCodeOperation(){
		
		List<String> listeCode = new ArrayList<>();
		
		List<RoleValue> listRoles = roleService.getAll();
		
		for(RoleValue r : listRoles)
			listeCode.add(r.getCode());
		
		
		
		
		return listeCode ;
	}
	

}
