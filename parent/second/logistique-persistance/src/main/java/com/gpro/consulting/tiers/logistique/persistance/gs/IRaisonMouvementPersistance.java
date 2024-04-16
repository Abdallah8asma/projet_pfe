package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRaisonMouvementPersistance.
 */
public interface IRaisonMouvementPersistance {


	  /**
  	 * Creer raison mouvement stock.
  	 *
  	 * @param pRaisonMouvementStock the raison mouvement stock
  	 * @return the string
  	 */
  	public String creerRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStock);

	  /**
  	 * Supprimer raison mouvement stock.
  	 *
  	 * @param pId the id
  	 */
  	public void supprimerRaisonMouvementStock(Long pId);
	
	  /**
  	 * Modifier raison mouvement stock.
  	 *
  	 * @param pRaisonMouvementStockValue the raison mouvement stock value
  	 * @return the string
  	 */
  	public String modifierRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  /**
  	 * Recherche raison mouvement stock par id.
  	 *
  	 * @param pRaisonMouvementStockValue the raison mouvement stock value
  	 * @return the raison mouvement stock value
  	 */
  	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  /**
  	 * Liste raison mouvement stock.
  	 *
  	 * @return the list
  	 */
  	public List < RaisonMouvementStockValue > listeRaisonMouvementStock();
	  
}
