package com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient;

import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ISoldeClientPersistance {

	public ResultatRechecheSoldeClientValue rechercherMultiCritere(
			RechercheMulticritereSoldeClientValue request);
	
	public SoldeClientValue getById(Long id);

	public String create(SoldeClientValue soldeValue);
	
	public String update(SoldeClientValue soldeValue);

	public void delete(Long id);

	public SoldeClientValue getByClientId(Long clientId);
	
}
