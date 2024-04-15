package com.gpro.consulting.tiers.commun.service.login;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;

public interface IUserService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(UserValue operationValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(UserValue operationValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UserValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserValue login(UserValue request);
	
	public UserValue findByUsername(String username);
}