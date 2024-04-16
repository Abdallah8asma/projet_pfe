package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;



// TODO: Auto-generated Javadoc
/**
 * The Interface IRaisonMouvementPersistance.
 */
public interface IRaisonFacturePersistance {


	  /**
  	 * Creer raison mouvement stock.
  	 *
  	 * @param pRaisonFacture the raison mouvement stock
  	 * @return the string
  	 */
  	public String creerRaisonFacture(RaisonFactureValue pRaisonFacture);

	  /**
  	 * Supprimer raison mouvement stock.
  	 *
  	 * @param pId the id
  	 */
  	public void supprimerRaisonFacture(Long pId);
	
	  /**
  	 * Modifier raison mouvement stock.
  	 *
  	 * @param pRaisonFactureValue the raison mouvement stock value
  	 * @return the string
  	 */
  	public String modifierRaisonFacture(RaisonFactureValue pRaisonFactureValue);

	  /**
  	 * Recherche raison mouvement stock par id.
  	 *
  	 * @param pRaisonFactureValue the raison mouvement stock value
  	 * @return the raison mouvement stock value
  	 */
  	public RaisonFactureValue rechercheRaisonFactureParId(RaisonFactureValue pRaisonFactureValue);

	  /**
  	 * Liste raison mouvement stock.
  	 *
  	 * @return the list
  	 */
  	public List < RaisonFactureValue > listeRaisonFacture();
	  
}
