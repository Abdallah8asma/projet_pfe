package com.gpro.consulting.tiers.logistique.service.gc.vente.facture;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;

/**
 * The Interface IRaisonMouvementService.
 */
public interface IRaisonFactureService {


	  /**
  	 * Creer raison mouvement stock.
  	 *
  	 * @param pRaisonFacture the raison mouvement stock
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String creerRaisonFacture(RaisonFactureValue pRaisonFacture);

	  /**
  	 * Supprimer raison mouvement stock.
  	 *
  	 * @param pId the id
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public void supprimerRaisonFacture(Long pId);
	
	  /**
  	 * Modifier raison mouvement stock.
  	 *
  	 * @param pRaisonFactureValue the raison mouvement stock value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String modifierRaisonFacture(RaisonFactureValue pRaisonFactureValue);

	  /**
  	 * Recherche raison mouvement stock par id.
  	 *
  	 * @param pRaisonFactureValue the raison mouvement stock value
  	 * @return the raison mouvement stock value
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public RaisonFactureValue rechercheRaisonFactureParId(RaisonFactureValue pRaisonFactureValue);

	  /**
  	 * Liste raison mouvement stock.
  	 *
  	 * @return the list
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public List < RaisonFactureValue > listeRaisonFacture();
	  
}
