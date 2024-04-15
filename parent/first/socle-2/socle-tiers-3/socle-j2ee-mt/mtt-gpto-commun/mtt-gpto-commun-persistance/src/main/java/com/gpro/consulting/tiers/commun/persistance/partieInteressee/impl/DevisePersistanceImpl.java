package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IDevisePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.DeviseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class DevisePersistanceImpl extends AbstractPersistance implements
										   IDevisePersistance {
	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(DevisePersistanceImpl.class);

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.commun.persistance.IDevisePersistance#creerDevise(com.gpro.consulting.tiers.commun.coordination.value.DeviseValue)
	 */
	@Override
	public String creerDevise(DeviseValue pDeviseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Creation du Devise "+ pDeviseValue.getDesignation() + " est en cours.");
					
		}
		DeviseEntite pDeviseEntite = (DeviseEntite) this
				.creer(PersistanceUtilities.toEntity(pDeviseValue));
		DeviseValue pDeviseValueResult = PersistanceUtilities
				.toValue(pDeviseEntite);
		return pDeviseValueResult.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.commun.persistance.IDevisePersistance#supprimerDevise(java.lang.Long)
	 */
	@Override
	public void supprimerDevise(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Suppression du Devise de l'ID=" + pId.longValue()+ " est en cours.");
					
		}
		this.supprimerEntite(DeviseEntite.class, pId.longValue());
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.commun.persistance.IDevisePersistance#modifierDevise(com.gpro.consulting.tiers.commun.coordination.value.DeviseValue)
	 */
	@Override
	public String modifierDevise(DeviseValue pDeviseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Modification du Devise de l'ID="+ pDeviseValue.getId().toString() + " est en cours.");
					
		}
		DeviseEntite pDeviseEntite = (DeviseEntite) this
				.modifier(PersistanceUtilities.toEntity(pDeviseValue));
		DeviseValue pDeviseValueResult = PersistanceUtilities
				.toValue(pDeviseEntite);
		return pDeviseValueResult.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.commun.persistance.IDevisePersistance#rechercheDeviseParId(com.gpro.consulting.tiers.commun.coordination.value.DeviseValue)
	 */
	@Override
	public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Recherche du Devise de l'ID="+ pDeviseValue.getId().toString() + " est en cours.");
					
		}
		DeviseEntite pDeviseEntite = (DeviseEntite) this.rechercherParId(
				pDeviseValue.getId().longValue(), DeviseEntite.class);
		DeviseValue pDeviseValueResult = PersistanceUtilities
				.toValue(pDeviseEntite);
		return pDeviseValueResult;

	}


	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.commun.persistance.IDevisePersistance#listeDevise()
	 */
	@Override
	public List<DeviseValue> listeDevise() {
		List<DeviseEntite> vListeDeviseEntite = this.lister(DeviseEntite.class);
		List<DeviseValue> vListDeviseValues = new ArrayList<DeviseValue>();
		for (DeviseEntite vDeviseEntite : vListeDeviseEntite) {
			vListDeviseValues.add(PersistanceUtilities.toValue(vDeviseEntite));
		}
		return vListDeviseValues;
	}

	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

}
