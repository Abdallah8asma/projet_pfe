package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IBanquePartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.BanqueEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class BanquePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class BanquePartieInteresseePersistanceImpl extends AbstractPersistance
		implements IBanquePartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerBanquePartieInteressee(BanqueValue pBanqueValue) {
		BanqueEntite pBanqueEntite = (BanqueEntite) this.creer(PersistanceUtilities.toEntity(pBanqueValue));
		BanqueValue pBanqueValueResult = PersistanceUtilities.toValue(pBanqueEntite);
		return pBanqueValueResult.getId().toString();
	}

	/**************** supprimer categorie partie interessee *******************/
	@Override
	public void supprimerBanquePartieInteressee(Long pBanqueValue) {
		this.supprimerEntite(BanqueEntite.class, pBanqueValue);
	}

	/**************** modifier categorie partie interessee *******************/
	@Override
	public String modifierBanquePartieInteressee(BanqueValue pBanqueValue) {
		BanqueEntite pBanqueEntite = (BanqueEntite) this
				.modifier(PersistanceUtilities.toEntity(pBanqueValue));
		BanqueValue pBanqueValueResult = PersistanceUtilities.toValue(pBanqueEntite);
		return pBanqueValueResult.getId().toString();
	}

	/****************
	 * recherche categorie partie interessee Par Id
	 *******************/

	@Override
	public BanqueValue rechercheBanquePartieInteresseeParId(BanqueValue pBanqueValue) {
		BanqueEntite pBanqueEntite = this.rechercherParId(pBanqueValue.getId().longValue(),
				BanqueEntite.class);
		BanqueValue pBanqueValueResult = PersistanceUtilities.toValue(pBanqueEntite);
		return pBanqueValueResult;
	}

	/**************** liste categorie partie interessee *******************/

	@Override
	public java.util.List<BanqueValue> listeBanquePartieInteressee() {
		List<BanqueEntite> vBanquesEntity = this.lister(BanqueEntite.class);
		List<BanqueValue> vListBanquesValues = new ArrayList<BanqueValue>();
		for (BanqueEntite vBanqueEntite : vBanquesEntity) {
			vListBanquesValues.add(PersistanceUtilities.toValue(vBanqueEntite));
		}
		return vListBanquesValues;
	}

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
