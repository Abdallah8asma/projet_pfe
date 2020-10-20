package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IRaisonFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.RaisonFactureEntite;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities.FacturePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EmplacementEntite;

// TODO: Auto-generated Javadoc
/**
 * The Class RaisonFacturePersistanceImpl.
 */

@Component
public class RaisonFacturePersistanceImpl extends AbstractPersistance implements IRaisonFacturePersistance{

	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	  
  	/** The Constant LOGGER. */
  	private static final Logger LOGGER = LoggerFactory.getLogger(EmplacementEntite.class);

	  
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#creerRaisonFacture(com.gpro.consulting.tiers.gs.coordination.value.RaisonFactureValue)
	 */
	@Override
	public String creerRaisonFacture(
			RaisonFactureValue pRaisonFacture) {
		 RaisonFactureEntite vRaisonFactureEntite = (RaisonFactureEntite) this.creer(FacturePersistanceUtilities.toEntity(pRaisonFacture));
		    if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Emplacement  d'ID=" + vRaisonFactureEntite.getId().toString() + " est en cours.");
		    }
		    return vRaisonFactureEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#supprimerRaisonFacture(java.lang.Long)
	 */
	@Override
	public void supprimerRaisonFacture(Long pId) {
		this.supprimerEntite(RaisonFactureEntite.class, pId.longValue());	
		
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#modifierRaisonFacture(com.gpro.consulting.tiers.gs.coordination.value.RaisonFactureValue)
	 */
	@Override
	public String modifierRaisonFacture(
			RaisonFactureValue pRaisonFactureValue) {
		RaisonFactureEntite vRaisonFactureEntite = (RaisonFactureEntite) this.modifier(FacturePersistanceUtilities.toEntity(pRaisonFactureValue));
	    if (LOGGER.isDebugEnabled()) {
	     // LOGGER.debug("Emplacement  d'ID=" + vRaisonFactureEntite.getId().toString() + " est en cours.");
	    }
	    return vRaisonFactureEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#rechercheRaisonFactureParId(com.gpro.consulting.tiers.gs.coordination.value.RaisonFactureValue)
	 */
	@Override
	public RaisonFactureValue rechercheRaisonFactureParId(
			RaisonFactureValue pRaisonFactureValue) {
		RaisonFactureEntite vRaisonFactureEntite = this.rechercherParId(pRaisonFactureValue.getId().longValue(),
			    RaisonFactureEntite.class);
		RaisonFactureValue vRaisonFactureValueResult = FacturePersistanceUtilities.toValue(vRaisonFactureEntite);
	 return vRaisonFactureValueResult;
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#listeRaisonFacture()
	 */
	@Override
	public List<RaisonFactureValue> listeRaisonFacture() {
		List < RaisonFactureEntite > vListRaisonFactureEntite = this.lister(RaisonFactureEntite.class);
	    List < RaisonFactureValue > vListRaisonFactureValue = new ArrayList < RaisonFactureValue >();
	    for (RaisonFactureEntite vRaisonFactureEntite : vListRaisonFactureEntite) {
	    	vListRaisonFactureValue.add(FacturePersistanceUtilities.toValue(vRaisonFactureEntite));
	    }
	    return vListRaisonFactureValue;
	}

	/* (non-Javadoc)
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


}
