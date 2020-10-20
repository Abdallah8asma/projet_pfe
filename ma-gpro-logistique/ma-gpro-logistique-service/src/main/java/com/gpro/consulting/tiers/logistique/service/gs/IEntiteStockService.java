package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechercheEntiteStockMouvementValue;


/**
 * The Interface IEntiteStockService.
 */
public interface IEntiteStockService {
	
	/******** recherche entite stock  for  Mouvement *******/
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);
	
	
 	/**
	  * Rechercher entite stock multi critere.
	  *
	  * @param pRechercheMulticritereEntiteStockValue the recherche multicritere entite stock value
	  * @return the resultat recheche entite stock stock value
	  */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);
     
	/******* list entite stock *********/
	// transaction methode
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<EntiteStockValue> listeEntiteStock();
	
	/******* creer entite stock ******/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue);

	/******** modifier entite stock ***********/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue);
	/*******supprimer enti stock*****/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerEntiteStock(Long pId);
	
	/****************recherche entite stock par id****************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId);
}
