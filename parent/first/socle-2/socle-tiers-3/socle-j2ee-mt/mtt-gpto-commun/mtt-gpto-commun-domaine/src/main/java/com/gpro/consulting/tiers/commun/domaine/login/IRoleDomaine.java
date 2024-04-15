package com.gpro.consulting.tiers.commun.domaine.login;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
/**
 * Role  Domaine interface
 * 
 * @author Nour
 *
 */
public interface IRoleDomaine {
	public String create(RoleValue operationValue);

	public String update(RoleValue operationValue);

	public void delete(Long id);

	public List<RoleValue> getAll();
	
	public RoleValue getById(Long id);
	
	public List<RoleValue> rechercherMultiCritere (RoleValue role);

	public RoleValue getByCode(String code);
	


}
