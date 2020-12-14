package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IMoldsPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MoldsEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class MoldsPersistanceImpl extends AbstractPersistance implements IMoldsPersistance {

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);

	/************************** Creation Molds *****************************/
	@Override
	public String creerMolds(MoldsValue pMoldsValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Creation du Molds " + pMoldsValue.getDesignation() + " est en cours.");
		}
		MoldsEntite pMoldsEntite = (MoldsEntite) this.creer(PersistanceUtilities.toEntity(pMoldsValue));
		MoldsValue pMoldsValueResult = PersistanceUtilities.toValue(pMoldsEntite);
		return pMoldsValueResult.getId().toString();
	}

	/************************** Suppression Molds *****************************/
	@Override
	public void supprimerMolds(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Suppression du Molds de l'ID=" + pId.longValue() + " est en cours.");
		}
		this.supprimerEntite(MoldsEntite.class, pId.longValue());
	}

	/************************ Modification Molds ******************************/
	@Override
	public String modifierMolds(MoldsValue pMoldsValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Modification du Molds de l'ID=" + pMoldsValue.getId().toString() + " est en cours.");
		}
		MoldsEntite pMoldsEntite = (MoldsEntite) this.modifier(PersistanceUtilities.toEntity(pMoldsValue));
		MoldsValue pMoldsValueResult = PersistanceUtilities.toValue(pMoldsEntite);
		return pMoldsValueResult.getId().toString();
	}

	/************************ Recherche Molds ******************************/
	@Override
	public MoldsValue rechercheMoldsParId(MoldsValue pMoldsValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Recherche du Molds de l'ID=" + pMoldsValue.getId().toString() + " est en cours.");
		}
		MoldsEntite pMoldsEntite = (MoldsEntite) this.rechercherParId(pMoldsValue.getId().longValue(),
				MoldsEntite.class);
		MoldsValue pMoldsValueResult = PersistanceUtilities.toValue(pMoldsEntite);
		return pMoldsValueResult;

	}

	/************************ Liste Molds ******************************/

	@Override
	public List<MoldsValue> listeMolds() {
		List<MoldsEntite> vListeMoldsEntite = this.lister(MoldsEntite.class);
		List<MoldsValue> vListMoldsValues = new ArrayList<MoldsValue>();
		for (MoldsEntite vMoldsEntite : vListeMoldsEntite) {
			vListMoldsValues.add(PersistanceUtilities.toValue(vMoldsEntite));
		}
		return vListMoldsValues;
	}

	/*****************************
	 * Getter & Setter
	 ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
