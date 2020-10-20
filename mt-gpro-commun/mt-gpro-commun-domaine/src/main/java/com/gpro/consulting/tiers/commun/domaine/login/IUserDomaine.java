package com.gpro.consulting.tiers.commun.domaine.login;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;

/**
 * Operation Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */
public interface IUserDomaine {

	public String create(UserValue operationValue);

	public UserValue getById(Long id);

	public String update(UserValue operationValue);

	public void delete(Long id);

	public List<UserValue> getAll();

	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request);
	
	public UserValue login(UserValue request);
	public UserValue findByUsername(String username);
}
