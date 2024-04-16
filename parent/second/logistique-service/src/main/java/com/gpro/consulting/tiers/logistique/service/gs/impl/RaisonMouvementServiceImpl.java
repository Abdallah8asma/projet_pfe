package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IRaisonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IRaisonMouvementService;

/**
 * The Class RaisonMouvementServiceImpl.
 */
@Service
@Transactional
public class RaisonMouvementServiceImpl   implements IRaisonMouvementService{
    
    /** The raison mouvement domaine. */
    @Autowired
    IRaisonMouvementDomaine raisonMouvementDomaine;
	
	/* (non-Javadoc)
	 * creer
	 */
	@Override
	public String creerRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStock) {
		return raisonMouvementDomaine.creerRaisonMouvementStock(pRaisonMouvementStock);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerRaisonMouvementStock(Long pId) {
            raisonMouvementDomaine.supprimerRaisonMouvementStock(pId);		
	}

	/* (non-Javadoc)
	 * modifier
	 */
	@Override
	public String modifierRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		   return raisonMouvementDomaine.modifierRaisonMouvementStock(pRaisonMouvementStockValue);
	}

	/* (non-Javadoc)
	 * recherche par id
	 */
	@Override
	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		  return raisonMouvementDomaine.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
	}

	/* (non-Javadoc)
	 * liste raison
	 * 
	 */
	@Override
	public List<RaisonMouvementStockValue> listeRaisonMouvementStock() {
		return raisonMouvementDomaine.listeRaisonMouvementStock();
	}
	
}
