package com.gpro.consulting.tiers.logistique.service.gc.soldeClient.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.soldeClient.ISoldeClientDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.soldeClient.ISoldeClientService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class SoldeClientServiceImpl implements ISoldeClientService {
	@Autowired
	ISoldeClientDomaine soldeClientDomaine;
	
	@Override
	public ResultatRechecheSoldeClientValue rechercherMultiCritere(
			RechercheMulticritereSoldeClientValue request) {
		return soldeClientDomaine.rechercherMultiCritere(request);
	}

	@Override
	public SoldeClientValue getById(Long id) {
		return soldeClientDomaine.getById(id);
	}

	@Override
	public String create(SoldeClientValue soldeValue) {
		return soldeClientDomaine.create(soldeValue);
	}
	
	@Override
	public String update(SoldeClientValue soldeValue) {
		return soldeClientDomaine.update(soldeValue);
	}

	@Override
	public void delete(Long id) {
		soldeClientDomaine.delete(id);		
	}
	
	@Override
	public Boolean updateSoldeClientForAllClients() {
		return soldeClientDomaine.updateSoldeClientForAllClients();
	}

}
