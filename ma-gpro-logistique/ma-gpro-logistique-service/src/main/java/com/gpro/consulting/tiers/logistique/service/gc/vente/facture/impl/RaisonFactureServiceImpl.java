package com.gpro.consulting.tiers.logistique.service.gc.vente.facture.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IRaisonFactureDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IRaisonFactureService;

/**
 * The Class RaisonMouvementServiceImpl.
 */
@Service
@Transactional
public class RaisonFactureServiceImpl   implements IRaisonFactureService{
    
    /** The raison mouvement domaine. */
    @Autowired
    IRaisonFactureDomaine raisonFactureDomaine;
	
	/* (non-Javadoc)
	 * creer
	 */
	@Override
	public String creerRaisonFacture(
			RaisonFactureValue pRaisonFacture) {
		return raisonFactureDomaine.creerRaisonFacture(pRaisonFacture);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerRaisonFacture(Long pId) {
            raisonFactureDomaine.supprimerRaisonFacture(pId);		
	}

	/* (non-Javadoc)
	 * modifier
	 */
	@Override
	public String modifierRaisonFacture(
			RaisonFactureValue pRaisonFactureValue) {
		   return raisonFactureDomaine.modifierRaisonFacture(pRaisonFactureValue);
	}

	/* (non-Javadoc)
	 * recherche par id
	 */
	@Override
	public RaisonFactureValue rechercheRaisonFactureParId(
			RaisonFactureValue pRaisonFactureValue) {
		  return raisonFactureDomaine.rechercheRaisonFactureParId(pRaisonFactureValue);
	}

	/* (non-Javadoc)
	 * liste raison
	 * 
	 */
	@Override
	public List<RaisonFactureValue> listeRaisonFacture() {
		return raisonFactureDomaine.listeRaisonFacture();
	}
	
}
