package com.gpro.consulting.tiers.logistique.service.gc.soldeClient;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ISoldeClientService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheSoldeClientValue rechercherMultiCritere(
			RechercheMulticritereSoldeClientValue request);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SoldeClientValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(SoldeClientValue soldeValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(SoldeClientValue soldeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Boolean updateSoldeClientForAllClients();
}
