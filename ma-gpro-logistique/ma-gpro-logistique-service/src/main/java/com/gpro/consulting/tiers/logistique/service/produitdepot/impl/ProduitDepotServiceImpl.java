package com.gpro.consulting.tiers.logistique.service.produitdepot.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.logistique.domaine.produitdepot.IProduitDepotDomaine;
import com.gpro.consulting.tiers.logistique.service.produitdepot.IProduitDepotService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProduitDepotServiceImpl implements IProduitDepotService {

	
	@Autowired
	IProduitDepotDomaine produitdepotDomaine;
	
	@Override
	public List<ProduitDepotValue> listeProduitDepot() {
		return produitdepotDomaine.listProduitDepot();
	}

	@Override
	public ResultatRechercheProduitDepotValue rechercherProduitDepotMultiCritere(
			RechercheMulticritereProduitDepotValue pResultatRechercheProduitDepotValue) {
		return produitdepotDomaine.rechercherMultiCritereProduitDepot(pResultatRechercheProduitDepotValue);
	}

}
