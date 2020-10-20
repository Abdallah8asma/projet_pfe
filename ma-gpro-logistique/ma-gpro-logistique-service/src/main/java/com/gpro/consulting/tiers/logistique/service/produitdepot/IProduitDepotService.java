package com.gpro.consulting.tiers.logistique.service.produitdepot;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;

public interface IProduitDepotService {
	
	 /**
	   * Liste produit.
	   *
	   * @return the list
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public List < ProduitDepotValue > listeProduitDepot();

	  // recherche multicriteres
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ResultatRechercheProduitDepotValue rechercherProduitDepotMultiCritere(
	    RechercheMulticritereProduitDepotValue pRechercheMulticritereProduitDepotValue);

	
	}


