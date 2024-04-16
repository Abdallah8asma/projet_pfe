package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IMouvementService.
 */
public interface IMouvementService {

 	/**
	  * Rechercher mouvement multi critere.
	  *
	  * @param pRechercheMulticritereMouvementValue the recherche multicritere mouvement value
	  * @return the resultat recheche mouvement value
	  */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue);
     
	/************** liste mouvement stock **************/
	// transaction methode
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MouvementStockValue> listeMouvementStock();
	
}
