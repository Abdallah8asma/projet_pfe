package com.gpro.consulting.tiers.commun.persistance.login;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
/**
 * Role  Persistance interface
 * 
 * @author Nour
 *
 */
public interface IRolePersistance {
	
	public String create(RoleValue operationValue);

	public String update(RoleValue operationValue);

	public void delete(Long id);

	public List<RoleValue> getAll();
	
	public List<RoleValue> rechercherMultiCritere (RoleValue role);
	
	public RoleValue getById(Long id);
public RoleValue findByRoleName(String roleName);

public RoleValue getByCode(String code);

}
