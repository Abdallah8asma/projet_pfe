package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IRaisonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.IRaisonMouvementPersistance;

@Component
public class RaisonMouvementDomaineImpl implements IRaisonMouvementDomaine{

	@Autowired
	IRaisonMouvementPersistance raisonMouvementPersistance;
	
	@Override
	public String creerRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStock) {
		return raisonMouvementPersistance.creerRaisonMouvementStock(pRaisonMouvementStock);
	}

	@Override
	public void supprimerRaisonMouvementStock(Long pId) {
		raisonMouvementPersistance.supprimerRaisonMouvementStock(pId);		
	}

	@Override
	public String modifierRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		   return raisonMouvementPersistance.modifierRaisonMouvementStock(pRaisonMouvementStockValue);
	}

	@Override
	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		return raisonMouvementPersistance.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
	}

	@Override
	public List<RaisonMouvementStockValue> listeRaisonMouvementStock() {
		   return raisonMouvementPersistance.listeRaisonMouvementStock();
	}
	

}
