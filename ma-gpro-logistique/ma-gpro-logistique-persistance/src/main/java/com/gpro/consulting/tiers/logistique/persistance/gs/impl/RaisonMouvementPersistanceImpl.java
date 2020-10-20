package com.gpro.consulting.tiers.logistique.persistance.gs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.IRaisonMouvementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EmplacementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.RaisonMouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.utilities.PersistanceUtilitiesGs;

// TODO: Auto-generated Javadoc
/**
 * The Class RaisonMouvementPersistanceImpl.
 */

@Component
public class RaisonMouvementPersistanceImpl extends AbstractPersistance implements IRaisonMouvementPersistance{

	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	  
  	/** The Constant LOGGER. */
  	private static final Logger LOGGER = LoggerFactory.getLogger(EmplacementEntite.class);

	  
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#creerRaisonMouvementStock(com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue)
	 */
	@Override
	public String creerRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStock) {
		 RaisonMouvementEntite vRaisonMouvementEntite = (RaisonMouvementEntite) this.creer(PersistanceUtilitiesGs.toEntity(pRaisonMouvementStock));
		    if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Emplacement  d'ID=" + vRaisonMouvementEntite.getId().toString() + " est en cours.");
		    }
		    return vRaisonMouvementEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#supprimerRaisonMouvementStock(java.lang.Long)
	 */
	@Override
	public void supprimerRaisonMouvementStock(Long pId) {
		this.supprimerEntite(RaisonMouvementEntite.class, pId.longValue());	
		
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#modifierRaisonMouvementStock(com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue)
	 */
	@Override
	public String modifierRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		RaisonMouvementEntite vRaisonMouvementEntite = (RaisonMouvementEntite) this.modifier(PersistanceUtilitiesGs.toEntity(pRaisonMouvementStockValue));
	    if (LOGGER.isDebugEnabled()) {
	     // LOGGER.debug("Emplacement  d'ID=" + vRaisonMouvementEntite.getId().toString() + " est en cours.");
	    }
	    return vRaisonMouvementEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#rechercheRaisonMouvementStockParId(com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue)
	 */
	@Override
	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		RaisonMouvementEntite vRaisonMouvementEntite = this.rechercherParId(pRaisonMouvementStockValue.getId().longValue(),
			    RaisonMouvementEntite.class);
		RaisonMouvementStockValue vRaisonMouvementStockValueResult = PersistanceUtilitiesGs.toValue(vRaisonMouvementEntite);
	 return vRaisonMouvementStockValueResult;
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance#listeRaisonMouvementStock()
	 */
	@Override
	public List<RaisonMouvementStockValue> listeRaisonMouvementStock() {
		List < RaisonMouvementEntite > vListRaisonMouvementEntite = this.lister(RaisonMouvementEntite.class);
	    List < RaisonMouvementStockValue > vListRaisonMouvementStockValue = new ArrayList < RaisonMouvementStockValue >();
	    for (RaisonMouvementEntite vRaisonMouvementEntite : vListRaisonMouvementEntite) {
	    	vListRaisonMouvementStockValue.add(PersistanceUtilitiesGs.toValue(vRaisonMouvementEntite));
	    }
	    return vListRaisonMouvementStockValue;
	}

	/* (non-Javadoc)
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


}
