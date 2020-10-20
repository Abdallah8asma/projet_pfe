package com.gpro.consulting.tiers.commun.service.login;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;

public interface IRoleService {
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(RoleValue operationValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(RoleValue operationValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RoleValue> getAll();
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RoleValue> rechercherMultiCritere(
			RoleValue request);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RoleValue getById(Long id);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RoleValue getByCode(String code);
}
