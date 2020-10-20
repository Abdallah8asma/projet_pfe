package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMouvementDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IMouvementService;

/**
 * The Class MouvementServiceImpl.
 */
@Service
@Transactional
public class MouvementServiceImpl     implements IMouvementService{

	/** The mouvement domaine. */
	@Autowired
	IMouvementDomaine mouvementDomaine;
	
	/* (non-Javadoc)
	 */
	@Override
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere( @RequestBody final RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {
		return mouvementDomaine.rechercherMouvementMultiCritere(pRechercheMulticritereMouvementValue);
	}

	/******list mouvement stock*****/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {
		return mouvementDomaine.listeMouvementStock();
	}

}
