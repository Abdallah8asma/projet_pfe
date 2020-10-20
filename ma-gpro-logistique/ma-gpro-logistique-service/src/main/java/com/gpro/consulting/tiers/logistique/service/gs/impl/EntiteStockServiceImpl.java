package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IEntiteStockDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IEntiteStockService;

// TODO: Auto-generated Javadoc
/**
 * The Class EntiteStockServiceImpl.
 */
@Service
@Transactional
public class EntiteStockServiceImpl implements IEntiteStockService{
	
	@Autowired
	IEntiteStockDomaine entiteStockDomaine;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IEntiteStockService#rechercherEntiteStockMultiCritere(com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue)
	 */
	@Override
	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
		return entiteStockDomaine.rechercherEntiteStockMultiCritere(pRechercheMulticritereEntiteStockValue);
	}
	/***liste entite stock*/
	@Override
	public List<EntiteStockValue> listeEntiteStock() {
		return entiteStockDomaine.listeEntiteStock();
	}
	
	
	/********recherche entite stock mouvement ***********/
	@Override
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
		return entiteStockDomaine.rechercherEntiteStockMouvement(pRechercheMulticritereEntiteStockValue);
	}
	@Override
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockDomaine.creerEntiteStock(pEntiteStockValue);
	}
	@Override
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockDomaine.modifierEntiteStock(pEntiteStockValue);
	}
	@Override
	public void supprimerEntiteStock(Long pId) {
         entiteStockDomaine.supprimerEntiteStock(pId);		
	}
	@Override
	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId) {
		return entiteStockDomaine.rechercheEntiteStockParId(pEntiteStockId);
	}

	
}
