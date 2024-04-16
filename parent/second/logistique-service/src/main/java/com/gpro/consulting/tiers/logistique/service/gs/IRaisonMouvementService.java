package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;

/**
 * The Interface IRaisonMouvementService.
 */
public interface IRaisonMouvementService {


	  /**
  	 * Creer raison mouvement stock.
  	 *
  	 * @param pRaisonMouvementStock the raison mouvement stock
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String creerRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStock);

	  /**
  	 * Supprimer raison mouvement stock.
  	 *
  	 * @param pId the id
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public void supprimerRaisonMouvementStock(Long pId);
	
	  /**
  	 * Modifier raison mouvement stock.
  	 *
  	 * @param pRaisonMouvementStockValue the raison mouvement stock value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String modifierRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  /**
  	 * Recherche raison mouvement stock par id.
  	 *
  	 * @param pRaisonMouvementStockValue the raison mouvement stock value
  	 * @return the raison mouvement stock value
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  /**
  	 * Liste raison mouvement stock.
  	 *
  	 * @return the list
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public List < RaisonMouvementStockValue > listeRaisonMouvementStock();
	  
}
