package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IUtilsEntitePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.UtilsEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class UtilsEntitePersistanceImpl extends AbstractPersistance implements IUtilsEntitePersistance {

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UtilsEntitePersistanceImpl.class);

	/************************** Creation Utils *****************************/
	@Override
	public String creerUtils(UtilsValue pUtilsValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Creation du Utils " + pUtilsValue.getDesignation() + " est
			// en cours.");
		}
		UtilsEntite pUtilsEntite = (UtilsEntite) this.creer(PersistanceUtilities.toEntity(pUtilsValue));
		UtilsValue pUtilsValueResult = PersistanceUtilities.toValue(pUtilsEntite);
		return pUtilsValueResult.getId().toString();
	}

	/************************** Suppression Utils *****************************/
	@Override
	public void supprimerUtils(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Suppression du Utils de l'ID=" + pId.longValue() + " est en
			// cours.");
		}
		this.supprimerEntite(UtilsEntite.class, pId.longValue());
	}

	/************************ Modification Utils ******************************/
	@Override
	public String modifierUtils(UtilsValue pUtilsValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Modification du Utils de l'ID=" +
			// pUtilsValue.getId().toString() + " est en cours.");
		}
		UtilsEntite pUtilsEntite = (UtilsEntite) this.modifier(PersistanceUtilities.toEntity(pUtilsValue));
		UtilsValue pUtilsValueResult = PersistanceUtilities.toValue(pUtilsEntite);
		return pUtilsValueResult.getId().toString();
	}

	/************************ Recherche Utils ******************************/
	@Override
	public UtilsValue rechercheUtilsParId(UtilsValue pUtilsValue) {
		if (LOGGER.isDebugEnabled()) {
			// LOGGER.debug(" ** Recherche du Utils de l'ID=" +
			// pUtilsValue.getId().toString() + " est en cours.");
		}
		UtilsEntite pUtilsEntite = (UtilsEntite) this.rechercherParId(pUtilsValue.getId().longValue(),
				UtilsEntite.class);
		UtilsValue pUtilsValueResult = PersistanceUtilities.toValue(pUtilsEntite);
		return pUtilsValueResult;

	}

	/************************ Liste Utils ******************************/

	@Override
	public List<UtilsValue> listeUtils() {
		List<UtilsEntite> vListeUtilsEntite = this.lister(UtilsEntite.class);
		List<UtilsValue> vListUtilsValues = new ArrayList<UtilsValue>();
		for (UtilsEntite vUtilsEntite : vListeUtilsEntite) {
			vListUtilsValues.add(PersistanceUtilities.toValue(vUtilsEntite));
		}
		return vListUtilsValues;
	}

	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<UtilsValue> listeUtilsParType(String type) {

		List<UtilsEntite> vListeUtilsEntite = this.rechercherParAttribut("type", type, UtilsEntite.class);
		List<UtilsValue> vListUtilsValues = new ArrayList<UtilsValue>();
		for (UtilsEntite vUtilsEntite : vListeUtilsEntite) {
			vListUtilsValues.add(PersistanceUtilities.toValue(vUtilsEntite));
		}
		return vListUtilsValues;
	}

}
