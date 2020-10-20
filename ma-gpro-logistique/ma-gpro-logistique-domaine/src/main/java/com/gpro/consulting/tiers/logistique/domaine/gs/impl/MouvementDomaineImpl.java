package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMouvementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMouvementPersistance;

@Component
public class MouvementDomaineImpl implements IMouvementDomaine{

	@Autowired
	IMouvementPersistance mouvementPersistance;
	
	@Override
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
			RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {
		
		return mouvementPersistance.rechercherMouvementMultiCritere(pRechercheMulticritereMouvementValue);
	}
	/***liste mouvement***/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {
		
		return mouvementPersistance.listeMouvementStock();
	}
	
	
}
